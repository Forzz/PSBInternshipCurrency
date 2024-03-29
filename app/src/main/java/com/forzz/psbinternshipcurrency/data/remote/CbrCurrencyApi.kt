package com.forzz.psbinternshipcurrency.data.remote

import com.forzz.psbinternshipcurrency.data.remote.dto.DailyResponseDTO
import retrofit2.http.GET

interface CbrCurrencyApi {

    @GET("/daily_json.js")
    suspend fun getDailyData(): DailyResponseDTO
}