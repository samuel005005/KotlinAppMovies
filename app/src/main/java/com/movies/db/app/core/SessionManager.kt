package com.movies.db.app.core

object SessionManager {
    lateinit var baseUrl: String
    lateinit var deviceAttestationToken: String

    init {

    }

    fun isBaseUrlInitialized(): Boolean {
        return SessionManager::baseUrl.isInitialized && baseUrl.isNotEmpty()
    }


    fun isDeviceAttestationTokenInitialized(): Boolean {
        return SessionManager::deviceAttestationToken.isInitialized && deviceAttestationToken.isNotEmpty()
    }


}