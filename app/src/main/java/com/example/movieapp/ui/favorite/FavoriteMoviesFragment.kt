package com.example.movieapp.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.movieapp.R
import com.example.movieapp.data.db.MovieDb
import com.example.movieapp.data.models.movie.Movie
import com.example.movieapp.databinding.FragmentFavoriteMoviesBinding
import com.example.movieapp.ui.adapters.MoviesDbAdapter
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_movies, container, false)

        val moviesDbAdapter = MoviesDbAdapter(emptyList()) {
            //Para realizar alguna acción al dar tap en la película favorita
        }

        binding.rvMovies.adapter = moviesDbAdapter

        val database = Room.databaseBuilder(
            requireContext(),
            MovieDb::class.java,
            "favorite-movie")
            .build()

        val myDao = database.getMovieDao()

        lifecycleScope.launch {
            moviesDbAdapter.favoriteMovies = myDao.getAllFavoriteMovie()
            binding.progressBar.visibility = View.INVISIBLE
            moviesDbAdapter.notifyDataSetChanged()
        }

        return binding.root
    }

}