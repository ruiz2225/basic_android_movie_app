package com.example.movieapp.views.popular

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.data.apiservice.NetworkClient
import com.example.movieapp.databinding.FragmentPopularMoviesBinding
import com.example.movieapp.views.adapters.MoviesAdapter
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
            Snackbar.make(binding.root, it.title, Snackbar.LENGTH_LONG).show()
        }

        binding.rvMovies?.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = "d310854107d722075ebbcaa7a2dba0fb"
            val language = "es"
            val popularMovies = NetworkClient.service.getListPopularMovie(apiKey, language)
            moviesAdapter.popularMovies = popularMovies.results
            binding.progressBar?.visibility = View.INVISIBLE
            moviesAdapter.notifyDataSetChanged()
        }

        return binding.root
    }
}