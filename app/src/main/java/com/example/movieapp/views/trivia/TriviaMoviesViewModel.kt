package com.example.movieapp.views.trivia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.data.models.trivia.Question

class TriviaMoviesViewModel: ViewModel() {

    private lateinit var questions : MutableList<Question>
    lateinit var currentQuestion: Question
    var questionIndex = 0
    var numQuestions = 0

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    private fun createQuestions(){
        questions = mutableListOf(
            Question(R.drawable.harry_potter, "¿Quién mata a la serpiente de Lord Voldemort?", listOf("Neville Longbottom","Harry Potter","Ron Weasley","Luna Lovegood"), 0),
            Question(R.drawable.back_to_the_future, "¿A qué año viajan Marty y Doc en Regreso al futuro II?", listOf("2005","2010","2015","2000"), 2),
            Question(R.drawable.star_wars, "¿En qué año se estrenó la primera película de Star Wars?", listOf("1975","1984","1980","1977"), 3),
            Question(R.drawable.inception, "¿Cuál es el director de la película Origen, estrenado en el 2010 y protagonizado por Leonardo DiCaprio?", listOf("Todd Phillips","James Cameron","Christopher Nolan","Martin Scorsese"), 2),
            Question(R.drawable.el_viaje_chihiro, "En el Viaje de Chihiro (2001) En qué se convierten sus papás por comer en el pueblo abandonado?", listOf("Dragones","Tortugas","Sapos","Cerdos"), 3),
            Question(R.drawable.the_mummy, "¿Bajo qué estatua se encontraba el sarcófago de Imhotep en La Momia?", listOf("Osiris","Anubis","Horus","Isis"), 1),
            Question(R.drawable.thor, "¿Cómo se llama el martillo de Thor?", listOf("Uru","Korg","Mjolnir","Hela"), 2)
        )
    }
    init {
        createQuestions()
        randomizeQuestions()
        _score.value = 0
        numQuestions = questions.size
    }

    fun setScore(correctOne: Boolean){
        questionIndex++
        if (questionIndex < questions.size) {
            currentQuestion = questions[questionIndex]
            if (correctOne) onCorrect() else onFailed()
            setQuestion()
        }
    }
    fun onCorrect(){
        _score.value = score.value?.plus(5)
    }
    fun onFailed(){
        _score.value = score.value?.minus(3)
    }
    fun randomizeQuestions(){
        questions.shuffle()
        setQuestion()
    }
    fun setQuestion(){
        currentQuestion = questions[questionIndex]
    }
    override fun onCleared() {
        super.onCleared()
        Log.d("TriviaMoviesViewModel", "ViewModel destruído!")
    }

}