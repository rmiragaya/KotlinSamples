package com.rodrigomiragaya.kotlinexamplessamples.retrofitPost

import androidx.lifecycle.*
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.JsonPlaseHolderRepository
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Post
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.ResponseWrapper
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.coroutines.launch

class RetrofitPostViewModel (state : SavedStateHandle) : StateViewModel(state) {

    private val repo = JsonPlaseHolderRepository()

    val post : MutableLiveData<ResponseWrapper<List<Post>>> = MutableLiveData()
    val getPostResponse : LiveData<ResponseWrapper<List<Post>>> = Transformations.map(repo.getPostResponse) { it }

    init{
        bindData("post", post)
    }

    fun pushPost (post : Post) {
        viewModelScope.launch {
            repo.pushPost(post)
        }
    }


}