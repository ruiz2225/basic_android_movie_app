package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.models.movie.PopularMovie
import com.example.movieapp.databinding.ItemMovieBinding

class MoviesAdapter(
    var popularMovies: List<PopularMovie>,
    private val movieClickedListener: (PopularMovie) -> Unit
    ):
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    //Crea una nueva vista cuando el RecyclerView se lo solicite y también podemos cargar diferentes tipos de vistas por medio de viewType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    //Nos devuelve el número de elementos del adapter
    override fun getItemCount() = popularMovies.size

    //Actualiza la vista del RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val popularMovie = popularMovies[position]
        holder.bind(popularMovie)
        holder.itemView.setOnClickListener { movieClickedListener(popularMovie) }
    }

    class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(popularMovie: PopularMovie){
            binding.txtTitle.text = popularMovie.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500"+popularMovie.poster_path)
                .into(binding.imgPosterPath)
        }
    }
}