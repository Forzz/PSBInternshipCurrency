package com.forzz.psbinternshipcurrency.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Extensions {
    companion object {
        fun Float.roundWithZeros(digits: Int): String {
            return String.format("%.${digits}f", this)
        }

        fun Date.convertToCustomDateString(): String {
            val dateFormat = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
            return dateFormat.format(this)
        }
    }
}