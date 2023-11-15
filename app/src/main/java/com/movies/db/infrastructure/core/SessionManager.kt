package com.movies.db.infrastructure.core

object SessionManager {
    lateinit var baseUrl: String
    lateinit var deviceAttestationToken: String

    init {

    }

    fun isBaseUrlInitialized(): Boolean {
        return ::baseUrl.isInitialized && baseUrl.isNotEmpty()
    }


    fun isDeviceAttestationTokenInitialized(): Boolean {
        return ::deviceAttestationToken.isInitialized && deviceAttestationToken.isNotEmpty()
    }


}