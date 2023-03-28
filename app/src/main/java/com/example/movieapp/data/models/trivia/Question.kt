package com.example.movieapp.data.models.trivia

data class Question(
    val image: Int,
    val question: String,
    val answers: List<String>,
    val correctOne: Int
    )
