package com.example.batmanmovies.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.batmanmovies.databinding.MovieItemBinding
import com.example.batmanmovies.presentation.entity.MovieEntity
import javax.inject.Inject

class MovieListAdapter @Inject constructor(private val glide: RequestManager):
    ListAdapter<MovieEntity, MovieListAdapter.MyHolder>(SearchCallBack()) {

    private var onItemClickListener: ((MovieEntity) -> Unit)? = null
    fun setOnItemClickListener(listener: (MovieEntity) -> Unit) {
        onItemClickListener = listener
    }

    inner class MyHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieEntity) {
            binding.apply {
                glide.load(item.poster)
                    .into(imgMoviePoster)
                txvMovieTitle.text = item.title
                txvMovieYear.text = item.year
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
            itemView.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(getItem(position))
                }
            }
        }
    }

    class SearchCallBack : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieEntity,
            newItem: MovieEntity
        ): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(
            oldItem: MovieEntity,
            newItem: MovieEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

}