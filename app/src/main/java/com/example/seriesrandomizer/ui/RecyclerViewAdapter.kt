package com.example.seriesrandomizer.ui

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesrandomizer.R
import com.example.seriesrandomizer.api.models.ShowDataClass
import com.example.seriesrandomizer.databinding.PopularShowItemBinding
import com.example.seriesrandomizer.util.ImageLoaderTool
import java.security.AccessController.getContext

class RecyclerViewAdapter(private val items: MutableList<ShowDataClass>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PopularShowItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun submitList(newItems: List<ShowDataClass>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: PopularShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShowDataClass) {
            with(binding) {
                showName.text = item.showName
                voteAverage.text = root.context.getString(
                    R.string.vote_average_string,
                    item.voteAverage.toString()
                )
                voteCount.text =
                    root.context.getString(R.string.vote_count_string, item.voteCount.toString())
                airDate.text = item.firstAirDate
                originalLanguage.text =
                    root.context.getString(R.string.original_language_string, item.originalLanguage)
                ImageLoaderTool(item.posterPath.orEmpty(), root.context).loadImage(posterImageView)
            }
        }
    }
}