package com.example.movieapp.views.trivia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TriviaMoviesViewModel: ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = 0
        Log.d("TriviaMoviesViewModel","ViewModel creado")
    }

    fun onCorrect(){
        _score.value = score.value?.plus(5)
    }

    fun onFailed(){
        _score.value = score.value?.minus(3)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TriviaMoviesViewModel", "ViewModel destruído!")
    }

}