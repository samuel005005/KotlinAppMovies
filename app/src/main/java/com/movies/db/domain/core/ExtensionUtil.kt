package com.movies.db.domain.core

import android.content.Context
import android.widget.Toast

fun String?.isErrorCode(): Boolean {
    val errorCode = this
    return errorCode == "0000" || errorCode == "000" || errorCode == "00" || errorCode == "0"
}

fun String?.isErrorCode200(): Boolean {
    val errorCode = this
    return errorCode == "200" || errorCode == "202" || errorCode == "0000"
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
