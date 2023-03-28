package com.example.movieapp.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailMovieBinding
import com.example.movieapp.ui.score.ScoreFragmentArgs

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

        binding.apply {
            txtTitleDetailMovie.text = args.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500"+args.backdropPath)
                .into(binding.imgBackdropPath)
            chipVoteAverage.text = args.voteAverage
            chipReleaseDate.text = args.releaseDate
            txtOverviewDetailMovie.text = args.overview
        }

        return binding.root
    }

}