package com.tdtruong.flicks.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tdtruong.flicks.databinding.FragmentMovieOverviewBinding

class MovieOverviewFragment : Fragment() {
    private val viewModel: MovieOverviewViewModel by lazy {
        ViewModelProvider(this).get(MovieOverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.listMovieTrending.adapter = MovieOverviewAdapter{
            movie ->  this.findNavController().navigate(MovieOverviewFragmentDirections.navMovieDetail(movie.id))
        }

        binding.listMovie.adapter = MovieOverviewAdapter{
                movie ->  this.findNavController().navigate(MovieOverviewFragmentDirections.navMovieDetail(movie.id))
        }

        return binding.root
    }
}