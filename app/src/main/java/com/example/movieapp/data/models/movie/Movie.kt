package com.example.movieapp.data.models.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id_movie") val idMovie: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "vote_average") val voteAverage: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "overview") val overview: String
)
