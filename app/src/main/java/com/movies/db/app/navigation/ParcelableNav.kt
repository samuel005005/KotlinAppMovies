package com.movies.db.app.navigation

import android.net.Uri
import android.os.Parcelable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

interface ParcelableNav {
    fun encodeValue(parcelable: Parcelable): String {
        return  Uri.encode(Json.encodeToJsonElement(parcelable).toString())
    }
}