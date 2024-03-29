package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonPlaseHolderRepository {

    var isLoading = MutableLiveData<Boolean>()
    var getPostResponse = MutableLiveData<ResponseWrapper<List<Post>>>()

    fun getPost() {
        isLoading.value = true
        RetrofitInstance.jsonPlaceHolderApi.getPost().enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getPost: finish")
                isLoading.value = false
            }
        })

        RetrofitInstance.jsonPlaceHolderApi.getPost().enqueue(
            object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getErrorPost() {
        isLoading.value = true
        RetrofitInstance.jsonPlaceHolderApi.getErrorPost().enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getErrorPost: finish")
                isLoading.value = false}
        })
    }

    fun getPostNumber(postNumber : Int) {
        isLoading.value = true
        RetrofitInstance.jsonPlaceHolderApi.getPostNumber(postNumber).enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getPostNumber: finish")
                isLoading.value = false}
        })
    }

    fun getUserIdPosts(userId : Int){
        isLoading.value = true
        RetrofitInstance.jsonPlaceHolderApi.getUserIdPosts(userId).enqueue(CallBackCustom<List<Post>>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(it) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getUserIdPosts: finish")
                isLoading.value = false
            }
        })
    }

    fun pushPost(post : Post){
        isLoading.value = true
        RetrofitInstance.jsonPlaceHolderApi.pushPost(post).enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getUserIdPosts: finish")
                isLoading.value = false}
        })
    }
}