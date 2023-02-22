package com.zeroninedev.common.di

import com.zeroninedev.common.constants.Constants.BASE_URL
import com.zeroninedev.common.data.api.MangaApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

/**
 * Base module for network response
 *
 */
@Module
class CoreNetworkModule {

    @Provides
    fun provideMangaApiService(retrofit: Retrofit): MangaApi = retrofit.create()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun client() = OkHttpClient().newBuilder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(provideLoggingInterceptor())
        .build()
}