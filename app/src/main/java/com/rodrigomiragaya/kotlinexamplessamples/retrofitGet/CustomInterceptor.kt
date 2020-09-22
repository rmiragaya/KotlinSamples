package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*


/** Interceptor to add header to all calls easily */
class CustomInterceptor :Interceptor {

    private val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US)

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-Timestamp", simpleDateFormat.format(Date()))
                .addHeader("authentication", "authenticationValue")
                .addHeader("tracking", "trackingValue")
                .build()
        )
    }

}