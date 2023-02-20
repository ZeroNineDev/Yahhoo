package com.zeroninedev.common.di

import android.util.Log
import com.zeroninedev.common.constants.Constants.BASE_URL
import com.zeroninedev.common.data.api.MangaApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
class CoreNetworkModule {

    @Provides
    fun provideMangaApiService(retrofit: Retrofit): MangaApi = retrofit.create()

    @Provides
    fun interceptor(): Interceptor = Interceptor{ chain ->
        val original: Request = chain.request()
        val response: Response = chain.proceed(original)
        Log.d(LOGGER_TAG, response.toString())
        return@Interceptor response
    }

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
    fun client(
        interceptor: Interceptor
    ) = OkHttpClient().newBuilder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(provideLoggingInterceptor())
        .build()

    companion object {

        private const val LOGGER_TAG = "CoreNetworkModule"
    }
}