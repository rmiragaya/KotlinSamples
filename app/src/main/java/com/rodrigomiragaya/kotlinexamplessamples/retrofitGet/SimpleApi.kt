package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import retrofit2.Call
import retrofit2.http.*


interface SimpleApi {

    //baseUrl + "/posts/x"
    @Headers("Ahutoto: 123123123")
    @GET("posts/1")
    fun getPost() : Call<Post>

    //error
    @GET("pts/1")
    fun getErrorPost() : Call<Post>

    //baseUrl + "/posts/x"
    @GET("posts/{postNumber}")
    fun getPostNumber( @Path("postNumber") number : Int) : Call<Post>

    //baseUrl + "/posts"
    @GET("posts")
    fun getUserIdPosts( @Query("userId") number : Int) : Call<List<Post>>

    //baseUrl + "/posts"
    @POST("posts")
    fun pushPost( @Body post: Post) : Call<Post>

}