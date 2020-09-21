package com.rodrigomiragaya.kotlinexamplessamples.retrofit

import java.io.Serializable

open class ResponseWrapper<T>(val value: T?) : Serializable {
    var errorResponse: ErrorResponse? = null

    constructor (code: String?, error: String?) : this(null) {
        errorResponse = ErrorResponse().apply {
            this.code = code
            this.description = error
        }
    }
}