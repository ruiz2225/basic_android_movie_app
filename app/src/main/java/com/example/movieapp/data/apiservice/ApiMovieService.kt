package com.example.movieapp.data.apiservice

import com.example.movieapp.data.models.movie.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovieService {

    @GET("movie/popular")
    suspend fun getListPopularMovie(@Query("api_key") apiKey:String, @Query("language") language:String): MovieResult

}