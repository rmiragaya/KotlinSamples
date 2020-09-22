package com.rodrigomiragaya.kotlinexamplessamples.recyclerViewGet

import androidx.lifecycle.*
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.JsonPlaseHolderRepository
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Post
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.ResponseWrapper
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.coroutines.launch

class RecyclerViewViewModel (state : SavedStateHandle) : StateViewModel(state) {

    private val repo = JsonPlaseHolderRepository()


    val post : MutableLiveData<ResponseWrapper<List<Post>>> = MutableLiveData()
    val getPostResponse : LiveData<ResponseWrapper<List<Post>>> = Transformations.map(repo.getPostResponse) { it }
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

    init{
        bindData("post", post)
    }

    fun getUserIdPosts(userId: Int){
        isLoading.value = true
        viewModelScope.launch {
            repo.getUserIdPosts(userId)
        }
    }

    fun setPost(listPost : ResponseWrapper<List<Post>>){
        post.value = listPost
        isLoading.value = false
    }


}