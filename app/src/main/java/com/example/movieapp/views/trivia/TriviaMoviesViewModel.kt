package com.example.movieapp.views.trivia

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TriviaMoviesViewModel: ViewModel() {

    val score = MutableLiveData<Int>()

    init {
        score.value = 0
        Log.d("TriviaMoviesViewModel","ViewModel creado")
    }

    fun onCorrect(){
        score.value = score.value?.plus(5)
    }

    fun onFailed(){
        score.value = score.value?.minus(3)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TriviaMoviesViewModel", "ViewModel destruído!")
    }

}