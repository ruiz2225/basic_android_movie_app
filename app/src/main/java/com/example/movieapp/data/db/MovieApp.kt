package com.example.movieapp.data.db

import android.app.Application
import androidx.room.Room

class MovieApp: Application() {

    val room = Room
        .databaseBuilder(this, MovieDb::class.java, "favorite_movies")
        .build()
}