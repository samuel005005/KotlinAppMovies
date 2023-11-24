package com.movies.db.shared.domain.core

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class HumanFormats {

    companion object {
        fun number(number: Double, decimals: Int = 0): String {
            val format = NumberFormat.getCurrencyInstance(Locale("en"))
            format.minimumFractionDigits = decimals
            format.maximumFractionDigits = decimals
            return format.format(number)
        }

        fun shortDate(date: Date): String {
            val format = SimpleDateFormat("yMMMEd", Locale("es"))
            return format.format(date)
        }
    }

}