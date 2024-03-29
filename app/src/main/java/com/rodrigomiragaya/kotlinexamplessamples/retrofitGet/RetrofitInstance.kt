package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import com.rodrigomiragaya.kotlinexamplessamples.BuildConfig
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Constants.Companion.BASE_URL_COUNTRIES
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Constants.Companion.BASE_URL_TYPECODE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/** Retrofit Instance */
object RetrofitInstance {
    private val READ_TIMEOUT = 40
    private var okHttpClientInstance: OkHttpClient? = null
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val retrofitTypicode by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TYPECODE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private val retrofitCountries by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_COUNTRIES)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    //specify the interface
    val jsonPlaceHolderApi : SimpleTypiApi by lazy {
        retrofitTypicode.create(SimpleTypiApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        if (okHttpClientInstance == null) {
            createInstanceOkHttpClient()
        }
        return okHttpClientInstance!!
    }


    private fun createInstanceOkHttpClient() {
        okHttpClientInstance = getBaseBuilder().build()
    }

    private fun getBaseBuilder(): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(CustomInterceptor())

        //show log with useful info (only on debug)
        if (BuildConfig.DEBUG) {
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return okHttpClientBuilder
    }
}

