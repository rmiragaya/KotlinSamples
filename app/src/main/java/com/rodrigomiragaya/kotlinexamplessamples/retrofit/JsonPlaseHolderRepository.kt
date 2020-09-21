package com.rodrigomiragaya.kotlinexamplessamples.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData

class JsonPlaseHolderRepository {

    var getPostResponse = MutableLiveData<ResponseWrapper<List<Post>>>()

    fun getPost() {
        RetrofitInstance.jsonPlaceHolderApi.getPost().enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getPost: finish") }
        })
    }

    fun getErrorPost() {
        RetrofitInstance.jsonPlaceHolderApi.getErrorPost().enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getErrorPost: finish") }
        })
    }

    fun getPostNumber(postNumber : Int) {
        RetrofitInstance.jsonPlaceHolderApi.getPostNumber(postNumber).enqueue(CallBackCustom<Post>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(listOf(it!!)) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getPostNumber: finish") }
        })
    }

    fun getUserIdPosts(userId : Int){
        RetrofitInstance.jsonPlaceHolderApi.getUserIdPosts(userId).enqueue(CallBackCustom<List<Post>>().apply {
            onSuccess = { getPostResponse.value = ResponseWrapper(it) }
            onServerError = { c, e -> getPostResponse.value = ResponseWrapper(c, e) }
            onNetworkError = { getPostResponse.value = ResponseWrapper("505", "Error genérico") }
            onFinish = { Log.d(TAG, "getUserIdPosts: finish") }
        })
    }
}