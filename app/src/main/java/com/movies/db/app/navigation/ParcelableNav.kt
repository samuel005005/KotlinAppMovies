package com.movies.db.app.navigation

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson

interface ParcelableNav {

    fun encodeValue(parcelable: Parcelable): String {
        return Uri.encode(Gson().toJson(parcelable))
    }
}