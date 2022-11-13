package com.zeroninedev.common.di

import android.util.Log
import com.zeroninedev.common.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CoreNetworkModule {

    @Provides
    fun interceptor(): Interceptor = Interceptor{ chain ->
        val original: Request = chain.request()
        val response: Response = chain.proceed(original)
        Log.d(LOGGER_TAG, response.message())
        return@Interceptor response
    }

    @Provides
    fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun client(interceptor: Interceptor) = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()

    companion object {
        private const val LOGGER_TAG = "CoreNetworkModule"
    }
}