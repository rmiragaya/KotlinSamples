package com.rodrigomiragaya.kotlinexamplessamples.retrofitGet

import androidx.lifecycle.*
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.coroutines.launch

class RetrofitActivityViewmodel (state : SavedStateHandle) : StateViewModel(state) {

    private val repo = JsonPlaseHolderRepository()

    val post : MutableLiveData<ResponseWrapper<List<Post>>> = MutableLiveData()
    val getPostResponse : LiveData<ResponseWrapper<List<Post>>> = Transformations.map(repo.getPostResponse) { it }

    init{
        bindData("post", repo.getPostResponse)
    }

    fun getPost () {
        viewModelScope.launch {
            repo.getPost()
        }
    }

    fun getErrorPost () {
        viewModelScope.launch {
            repo.getErrorPost()
        }
    }

    fun getPostNumber(postNumber : Int){
        viewModelScope.launch {
            repo.getPostNumber(postNumber)
        }
    }

    fun getUserIdPosts(userId: Int){
        viewModelScope.launch {
            repo.getUserIdPosts(userId)
        }
    }
}