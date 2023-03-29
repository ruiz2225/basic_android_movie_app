package com.example.movieapp.ui.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.db.MovieApp
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.db.MovieDb
import com.example.movieapp.data.models.movie.Movie
import com.example.movieapp.databinding.FragmentDetailMovieBinding
import com.example.movieapp.ui.score.ScoreFragmentArgs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DetailMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)

        val database = Room.databaseBuilder(
            requireContext(),
            MovieDb::class.java,
            "favorite-movie")
            .build()

       val myDao = database.getMovieDao()

       binding.apply {
            txtTitleDetailMovie.text = args.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500"+args.backdropPath)
                .into(binding.imgBackdropPath)
            chipVoteAverage.text = args.voteAverage
            chipReleaseDate.text = args.releaseDate
           lifecycleScope.launch {
                Log.d("ROOM_MOVIE", "TITLE -> "+myDao.getMovieById(args.id))
               if(myDao.getMovieById(args.id).isNullOrEmpty()){
                   chipFavorite.text = resources.getString(R.string.txt_chip_add_favorite)
               }else{
                   chipFavorite.text = resources.getString(R.string.txt_chip_delete_favorite)
               }
           }
            txtOverviewDetailMovie.text = args.overview
        }

        binding.chipFavorite.setOnClickListener {
            var newFavoriteMovie = Movie(
                idMovie = args.id,
                title = args.title,
                posterPath = args.posterPath,
                voteAverage = args.voteAverage,
                releaseDate = args.releaseDate,
                overview = args.overview
            )
            lifecycleScope.launch {
                if(myDao.getMovieById(args.id).isNullOrEmpty()){
                    addFavoriteMovie(myDao, newFavoriteMovie)
                }else{
                    deleteFavoriteMovie(myDao, args.id)
                }
            }
        }

        return binding.root
    }

    fun addFavoriteMovie(movieDao: MovieDao,newFavoriteMovie: Movie){
        lifecycleScope.launch {
            movieDao.insertFavoriteMovie(newFavoriteMovie)
        }
        binding.chipFavorite.text = resources.getString(R.string.txt_chip_delete_favorite)
    }

    fun deleteFavoriteMovie(movieDao: MovieDao, id: Int){
        lifecycleScope.launch {
            movieDao.deleteFavoriteMovie(id)
        }
        binding.chipFavorite.text = resources.getString(R.string.txt_chip_add_favorite)
    }

}