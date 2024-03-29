package com.forzz.psbinternshipcurrency.di

import com.forzz.psbinternshipcurrency.data.remote.CbrCurrencyApi
import com.forzz.psbinternshipcurrency.data.repository.CurrencyRepositoryImpl
import com.forzz.psbinternshipcurrency.domain.repository.CurrencyRepository
import com.forzz.psbinternshipcurrency.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_CBR_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): CbrCurrencyApi {
        return retrofit.create(CbrCurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CbrCurrencyApi): CurrencyRepository {
        return CurrencyRepositoryImpl(api)
    }
}