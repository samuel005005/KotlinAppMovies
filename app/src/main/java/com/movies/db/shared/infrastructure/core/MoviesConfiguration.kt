package com.movies.db.shared.infrastructure.core

import android.app.Application
import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import io.realm.Realm
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Arrays
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject

class MoviesConfiguration @Inject constructor(private val application: Application) {
    private val realmKey = getKey()


    init {
        Arrays.fill(realmKey, 0.toByte())
    }

    fun getKey(): ByteArray {
        application.applicationContext
            ?.getSharedPreferences("realm_key", Context.MODE_PRIVATE)
            ?.getString("iv_and_encrypted_key", null)

        return if (application.applicationContext
                ?.getSharedPreferences("realm_key", Context.MODE_PRIVATE)
                ?.getString("iv_and_encrypted_key", null) == null
        ) {
            getNewKey()

        } else {
            getExistingKey()
        }
    }

    private fun getExistingKey(): ByteArray {
        // open a connection to the android keystore
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        // access the encrypted key that's stored in shared preferences
        val initializationVectorAndEncryptedKey = Base64.decode(
            application.applicationContext
                ?.getSharedPreferences("realm_key", Context.MODE_PRIVATE)
                ?.getString("iv_and_encrypted_key", null), Base64.DEFAULT
        )

        //  -> val initializationVectorAndEncryptedKey = Base64.decode(SessionManager.realmKey ?: "", Base64.DEFAULT)

        val buffer = ByteBuffer.wrap(initializationVectorAndEncryptedKey)
        buffer.order(ByteOrder.BIG_ENDIAN)
        // extract the length of the initialization vector from the buffer
        val initializationVectorLength = buffer.int
        // extract the initialization vector based on that length
        val initializationVector = ByteArray(initializationVectorLength)
        buffer[initializationVector]
        // extract the encrypted key
        val encryptedKey = ByteArray(
            initializationVectorAndEncryptedKey.size
                    - Integer.BYTES
                    - initializationVectorLength
        )
        buffer[encryptedKey]
        // create a cipher that uses AES encryption to decrypt our key
        val cipher: Cipher = try {
            Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES
                        + "/" + KeyProperties.BLOCK_MODE_CBC
                        + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        // decrypt the encrypted key with the secret key stored in the keystore
        val decryptedKey: ByteArray = try {
            val secretKey: SecretKey = keyStore.getKey("realm_key", null) as SecretKey
            val initializationVectorSpec = IvParameterSpec(initializationVector)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, initializationVectorSpec)
            cipher.doFinal(encryptedKey)
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        return decryptedKey // pass to a realm configuration via encryptionKey()
    }

    private fun getNewKey(): ByteArray {
        // open a connection to the android keystore
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        // create a securely generated random asymmetric RSA key
        val realmKey = ByteArray(Realm.ENCRYPTION_KEY_LENGTH)
        SecureRandom().nextBytes(realmKey)
        // create a cipher that uses AES encryption -- we'll use this to encrypt our key
        val cipher: Cipher = try {
            Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES
                        + "/" + KeyProperties.BLOCK_MODE_CBC
                        + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        // generate secret key
        val keyGenerator: KeyGenerator = try {
            KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                "AndroidKeyStore"
            )
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
        val keySpecBuilder = KeyGenParameterSpec.Builder(
            "realm_key",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setUserAuthenticationRequired(false)

        val keySpec = keySpecBuilder.build()
        try {
            keyGenerator.init(keySpec)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e)
        }
        keyGenerator.generateKey()
        // access the generated key in the android keystore, then
        // use the cipher to create an encrypted version of the key
        val initializationVector: ByteArray
        val encryptedKeyForRealm: ByteArray
        try {
            val secretKey = keyStore.getKey("realm_key", null) as SecretKey
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            encryptedKeyForRealm = cipher.doFinal(realmKey)
            initializationVector = cipher.iv
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        // keep the encrypted key in shared preferences
        // to persist it across application runs
        val initializationVectorAndEncryptedKey = ByteArray(
            Integer.BYTES +
                    initializationVector.size +
                    encryptedKeyForRealm.size
        )
        val buffer = ByteBuffer.wrap(initializationVectorAndEncryptedKey)
        buffer.order(ByteOrder.BIG_ENDIAN)
        buffer.putInt(initializationVector.size)
        buffer.put(initializationVector)
        buffer.put(encryptedKeyForRealm)

        application.applicationContext.getSharedPreferences("realm_key", Context.MODE_PRIVATE)
            .edit()
            .putString(
                "iv_and_encrypted_key",
                Base64.encodeToString(initializationVectorAndEncryptedKey, Base64.NO_WRAP)
            )
            .apply()

        return realmKey // pass to a realm configuration via encryptionKey()
    }
}