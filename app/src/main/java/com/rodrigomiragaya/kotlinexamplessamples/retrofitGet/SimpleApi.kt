package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SimpleApi {

    @GET("posts/1")
    fun getPost() : Call<Post>

    @GET("pts/1")
    fun getErrorPost() : Call<Post>

    @GET("posts/{postNumber}")
    fun getPostNumber( @Path("postNumber") number : Int) : Call<Post>

    @GET("posts")
    fun getUserIdPosts( @Query("userId") number : Int) : Call<List<Post>>

}