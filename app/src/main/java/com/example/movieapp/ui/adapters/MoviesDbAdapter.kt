package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.models.movie.Movie
import com.example.movieapp.data.models.movie.PopularMovie
import com.example.movieapp.databinding.ItemMovieBinding

class MoviesDbAdapter(
    var favoriteMovies: List<Movie>,
    private val movieClickedListener: (Movie) -> Unit
):
RecyclerView.Adapter<MoviesDbAdapter.ViewHolder>() {

    //Crea una nueva vista cuando el RecyclerView se lo solicite y también podemos cargar diferentes tipos de vistas por medio de viewType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    //Nos devuelve el número de elementos del adapter
    override fun getItemCount() = favoriteMovies.size

    //Actualiza la vista del RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteMovies = favoriteMovies[position]
        holder.bind(favoriteMovies)
        holder.itemView.setOnClickListener { movieClickedListener(favoriteMovies) }
    }

    class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(favoriteMovies: Movie){
            binding.txtTitle.text = favoriteMovies.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500"+favoriteMovies.posterPath)
                .into(binding.imgPosterPath)
        }
    }
}