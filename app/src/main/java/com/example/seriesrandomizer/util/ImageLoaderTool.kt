package com.example.seriesrandomizer.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.seriesrandomizer.R

class ImageLoaderTool(private val imagePath: String, private val context: Context) {
    companion object {
        const val BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    fun loadImage(target: ImageView) {
        Glide.with(context)
            .load(BASE_URL + imagePath)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(target)
    }
}