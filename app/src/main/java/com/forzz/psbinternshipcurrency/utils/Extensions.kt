package com.forzz.psbinternshipcurrency.utils

class Extensions {
    companion object {
        fun Float.roundWithZeros(digits: Int): String {
            return String.format("%.${digits}f", this)
        }
    }
}