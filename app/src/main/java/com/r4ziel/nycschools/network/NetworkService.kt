package com.r4ziel.nycschools.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class NetworkService {

    companion object {
        private const val HOST = "data.cityofnewyork.us"
        private const val PROTOCOL = "https"
        private const val PATH = "/resource/"
        private const val WRITE_TIMEOUT = 60
        private const val READ_TIMEOUT = 180
        private const val CONNECT_TIMEOUT = 90
        private const val baseUrl = "$PROTOCOL://$HOST$PATH"
    }

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val moshiConverterFactory: MoshiConverterFactory = MoshiConverterFactory.create(moshi)


    fun getRetrofitInstance() : Retrofit {
        val okHttpClient = getOkHttpClient()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                try {
                    Log.v("OkHttp", message)
                } catch (e: Exception) {
                    throw e
                }
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okBuilder.addInterceptor(loggingInterceptor)
        return okBuilder.build()
    }
}