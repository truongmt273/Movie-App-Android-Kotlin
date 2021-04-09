package com.tdtruong.flicks.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tdtruong.flicks.databinding.ItemMovieBinding
import com.tdtruong.flicks.models.Movie

class MovieOverviewAdapter(
    val movieClickCallback: (Movie) -> Unit
) : PagedListAdapter<Movie, MovieOverviewAdapter.MovieOverviewViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieOverviewViewHolder {
        val itemBiding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieOverviewViewHolder(itemBiding)
    }

    override fun onBindViewHolder(holder: MovieOverviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieOverviewViewHolder(private val itemBiding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBiding.root) {
        fun bind(movie: Movie?) {
            itemBiding.movie = movie
            itemBiding.executePendingBindings()
            itemBiding.root.setOnClickListener {
                movie.let { movieClickCallback.invoke(it!!) }
            }
//            itemBiding.cardImage.let {
//                Picasso.with(itemView.context)
//                    .load(BASE_IMAGE_URL + movie.posterPath)
//                    .into(it)
//            }

        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (movies: Movie) -> Unit) {
        fun onClick(movies: Movie) = clickListener(movies)
    }
}