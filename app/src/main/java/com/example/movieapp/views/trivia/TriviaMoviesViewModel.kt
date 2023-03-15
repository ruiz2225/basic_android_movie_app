package com.example.movieapp.views.trivia

import android.util.Log
import androidx.lifecycle.ViewModel

class TriviaMoviesViewModel: ViewModel() {

    var score = 0

    init {
        Log.d("TriviaMoviesViewModel","ViewModel creado")
    }

    fun onCorrect(){
        score += 5
    }

    fun onFailed(){
        score -= 3
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TriviaMoviesViewModel", "ViewModel destruído!")
    }

}