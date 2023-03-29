package com.example.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.models.movie.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDb: RoomDatabase(){

    abstract fun getMovieDao(): MovieDao

}