package com.tdtruong.flicks.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.tdtruong.flicks.databinding.FragmentMovieDetailBinding
import com.tdtruong.flicks.models.Movie


class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var mVideoView: YouTubePlayerView
    private lateinit var movie: Movie
    private lateinit var viewModel: MovieDetailViewModel

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val application = requireNotNull(activity).application
        binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val movieId: Int = arguments.let { MovieDetailFragmentArgs.fromBundle(it!!).selectedMovie }

        val viewModelFactory = MovieDetailViewModelFactory(movieId, application)
        binding.movieDetailViewmodel = ViewModelProvider(
            this, viewModelFactory
        ).get(MovieDetailViewModel::class.java)


//        val viewModel: MovieDetailViewModel by lazy {
//            ViewModelProvider(
//                this, viewModelFactory
//            ).get(MovieDetailViewModel::class.java)
//        }

//        movie = binding.movieDetailViewmodel.selectedMovie.value

//        binding.movieDetailViewmodel = viewModel

        mVideoView = binding.moviePlayerView

        binding.moviePlayButton.setOnClickListener {
            mVideoView.visibility = View.VISIBLE
            mVideoView.getPlayerUiController().showFullscreenButton(true)
            mVideoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val videoId = getUrlTrailer(movie)
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })

            mVideoView.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
                if (mVideoView.isFullScreen()) {
                    mVideoView.exitFullScreen()
                    (activity as AppCompatActivity).window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    // Show ActionBar
                    if ((activity as AppCompatActivity).supportActionBar != null) {
                        (activity as AppCompatActivity).supportActionBar!!.show()
                    }
                } else {
                    mVideoView.enterFullScreen()
                    (activity as AppCompatActivity).window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                    // Hide ActionBar
                    if ((activity as AppCompatActivity).supportActionBar != null) {
                        (activity as AppCompatActivity).supportActionBar!!.hide()
                    }
                }
            })
        }

        initObserver()

        return binding.root
    }

    private fun getUrlTrailer(movie: Movie?): String {
        val listVideos = movie!!.videos.results
        for (video in listVideos) {
            if (video.type.equals("Trailer")) {
                return video.key
            }
        }
        return null.toString()
    }

    private fun initObserver() {
        binding.movieDetailViewmodel?.selectedMovie?.observe(viewLifecycleOwner, Observer {
            movie = it
        })
    }
}