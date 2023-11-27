package com.movies.db.app.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.json.Json

inline fun <reified T : Parcelable> NavType.Companion.parcelableTypeOf() =
    object : NavType<T>(isNullableAllowed = true) {
        override fun get(bundle: Bundle, key: String): T? {
//            return bundle.getParcelable(key, T::class.java)
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }
    }