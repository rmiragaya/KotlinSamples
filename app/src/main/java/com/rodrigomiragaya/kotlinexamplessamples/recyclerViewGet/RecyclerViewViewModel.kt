package com.rodrigomiragaya.kotlinexamplessamples.recyclerViewGet

import androidx.lifecycle.*
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.JsonPlaseHolderRepository
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Post
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.ResponseWrapper
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.coroutines.launch

class RecyclerViewViewModel (state : SavedStateHandle) : StateViewModel(state) {

    private val repo = JsonPlaseHolderRepository()

    val isLoading : LiveData<Boolean> = Transformations.map(repo.isLoading) { it }
//    val post : MutableLiveData<ResponseWrapper<List<Post>>> = MutableLiveData()
    val getPostResponse : LiveData<ResponseWrapper<List<Post>>> = Transformations.map(repo.getPostResponse) { it }


    init{
        bindData("post", repo.getPostResponse)
    }

    fun getUserIdPosts(userId: Int){
        viewModelScope.launch {
            repo.getUserIdPosts(userId)
        }
    }

//    fun setPost(listPost : ResponseWrapper<List<Post>>){
//        isLoading.value = false
//        post.value = listPost
//    }


}