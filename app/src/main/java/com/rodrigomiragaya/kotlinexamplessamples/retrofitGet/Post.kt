package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post(
    val userId: Int,
    val id: Int,
    @SerializedName("title")
    val titulo: String,
    val body: String
) : Serializable {
    override fun toString(): String {
        return "Post(userId=$userId, id=$id, titulo='$titulo', body='$body')"
    }
}