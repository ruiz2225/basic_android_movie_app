package com.example.movieapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.data.models.movie.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertFavoriteMovie(movieDB: Movie)

    @Query("DELETE FROM movie_table WHERE id_movie = :idMovie")
    suspend fun deleteFavoriteMovie(idMovie: Int)

    @Query("SELECT * FROM movie_table")
    suspend fun getAllFavoriteMovie(): List<Movie>

    @Query("SELECT title FROM movie_table WHERE id_movie = :idMovie")
    suspend fun getMovieById(idMovie: Int): String

}