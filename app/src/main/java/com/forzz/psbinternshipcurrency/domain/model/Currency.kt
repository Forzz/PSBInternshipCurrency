package com.forzz.psbinternshipcurrency.domain.model

data class Currency(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Float,
    val previous: Float,
)
