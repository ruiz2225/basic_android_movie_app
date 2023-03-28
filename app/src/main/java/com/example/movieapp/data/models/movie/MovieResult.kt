package com.example.movieapp.data.models.movie

data class MovieResult(
    val page: Int,
    val results: List<PopularMovie>,
    val total_pages: Int,
    val total_results: Int
)