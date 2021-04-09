package com.tdtruong.flicks.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tdtruong.flicks.R
import com.tdtruong.flicks.models.Movie
import com.tdtruong.flicks.ui.overview.MovieOverviewAdapter

//@BindingAdapter("imageUrl")
//fun bindImage(imgView: AppCompatImageView, url: String) {
//    if (url.isNotBlank()) {
//        Glide.with(imgView.context)
//            .load(BASE_IMAGE_URL + url)
//            .dontAnimate()
//            .into(imgView)
//    } else  {
//        Glide.with(imgView.context)
//            .load("https://dd.b.pvp.net/2_0_0/set1/en_us/img/cards/01IO012.png")
//            .into(imgView)
//    }
//}

@BindingAdapter("imageUrl")
fun bindImage(imgView: AppCompatImageView, imgUrl: String?) {
    imgUrl?.let {
//        val imgUri = (BASE_IMAGE_URL + imgUrl).toUri().buildUpon().scheme("https").build()
        val imgUri = (BASE_IMAGE_URL + imgUrl).toUri()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PagedList<Movie>?) {
    val adapter = recyclerView.adapter as MovieOverviewAdapter
    adapter.submitList(data)
}

@BindingAdapter("setTextMovie")
fun bindTextMovie(txtView: AppCompatTextView, content: String?) {
    txtView.text = content
}

@BindingAdapter("bindLengthMovie")
fun bindIntTextMovie(txtView: AppCompatTextView, content: Int?) {
    txtView.text = content.toString() + " min"
}

@BindingAdapter("bindYearMovie")
fun bindYearMovie(txtView: AppCompatTextView, content: String?) {
    txtView.text = content?.substring(0, 4)
}

@BindingAdapter("bindCountryMovie")
fun bindCountryMovie(txtView: AppCompatTextView, content: String?) {
    if (content.equals("United States of America")) {
        txtView.text = "USA"
    }
    else txtView.text = content
}