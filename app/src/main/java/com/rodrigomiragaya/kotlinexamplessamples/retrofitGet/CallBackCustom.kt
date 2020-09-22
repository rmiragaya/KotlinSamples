package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import okhttp3.Headers
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

open class CallBackCustom<T> : retrofit2.Callback<T> {

    private var isSuccessful = false
    var onSuccess: ((T?) -> Unit)? = null
    var onNetworkError: (() -> Unit)? = null
    var onServerError: ((String?, String?) -> Unit)? = null
    var onFinish: ((Boolean?) -> Unit)? = null

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            isSuccessful = true
            onSuccess?.invoke(response.body())
            response.headers()

        } else {
            val errorResponse: ErrorResponse? = getErrorResponse(response)
            onServerError?.invoke(response.code().toString(), errorResponse?.description)
//            when (response.code()) {
//                401 -> {
//                    //can customize
//                    try {
//                        //var credentialsErrorResponse : CredentialsErrorResponse = CredentialsErrorResponse("",errorResponse?.error,errorResponse?.response)
//                        //val response2 = GsonBuilder().create().fromJson(response.errorBody().toString(), CredentialsErrorResponse::class.java)
//                        if (errorResponse != null) {
//                            errorResponse.code = response.raw().code().toString()
//                        }
//                        onUnauthorized(errorResponse)
//                    } catch (e: Exception) {
//                        onUnauthorized.invoke(null)
//                    }
//                }
//                406 -> {
//                    try {
//                        //var credentialsErrorResponse : CredentialsErrorResponse = CredentialsErrorResponse("",errorResponse?.error,errorResponse?.response)
//                        //val response2 = GsonBuilder().create().fromJson(response.errorBody().toString(), CredentialsErrorResponse::class.java)
//                        if (errorResponse != null) {
//                            errorResponse.codigo = response.raw().code().toString()
//                        }
//                        onUnauthorized(errorResponse)
//                    } catch (e: Exception) {
//                        onUnauthorized.invoke(null)
//                    }
//                }
//                else -> {
//                    onServerError?.invoke(response.code().toString(), errorResponse?.description)
//                }
//            }
        }
        onFinish?.invoke(isSuccessful)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onNetworkError?.invoke()
        onFinish?.invoke(isSuccessful)
    }

    private fun getErrorResponse(response: Response<T>): ErrorResponse? {
        try {
            return GsonConverterFactory.create().responseBodyConverter(
                ErrorResponse::class.java!!,
                arrayOfNulls(0),
                null
            )?.convert(response.errorBody()!!) as ErrorResponse?
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun getHeaders(headers: Headers): HashMap<String, String> {
        val result = HashMap<String, String>()
        for (i in 0 until headers.size()) {
            result[headers.name(i)] = headers.value(i)
        }
        return result
    }
}