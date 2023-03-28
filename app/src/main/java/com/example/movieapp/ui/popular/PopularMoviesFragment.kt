package com.example.movieapp.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.data.apiservice.NetworkClient
import com.example.movieapp.data.models.movie.MovieResult
import com.example.movieapp.data.models.movie.PopularMovie
import com.example.movieapp.databinding.FragmentPopularMoviesBinding
import com.example.movieapp.ui.adapters.MoviesAdapter
import com.example.movieapp.ui.trivia.TriviaMoviesFragmentDirections
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PopularMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopularMoviesFragment : Fragment() {

    private lateinit var binding: FragmentPopularMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false)

        val moviesAdapter = MoviesAdapter(emptyList()) {
            navigateToDetail(it)
        }

        binding.rvMovies?.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = "d310854107d722075ebbcaa7a2dba0fb"
            val language = "en"
            val popularMovies = NetworkClient.service.getListPopularMovie(apiKey, language)
            moviesAdapter.popularMovies = popularMovies.results
            binding.progressBar?.visibility = View.INVISIBLE
            moviesAdapter.notifyDataSetChanged()
        }

        return binding.root
    }

    fun navigateToDetail(movie: PopularMovie){
        val action = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToDetailMovieFragment(
            movie.title,
            movie.backdrop_path,
            movie.vote_average.toString(),
            movie.release_date,
            movie.overview)
        findNavController().navigate(action)
    }
}