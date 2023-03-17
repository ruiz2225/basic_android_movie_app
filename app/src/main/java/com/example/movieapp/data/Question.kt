package com.example.movieapp.data

data class Question(
    val image: Int,
    val question: String,
    val answers: List<String>,
    val correctOne: Int
    )
