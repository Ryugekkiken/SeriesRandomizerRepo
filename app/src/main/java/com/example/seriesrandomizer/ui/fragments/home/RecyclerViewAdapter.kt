package com.example.seriesrandomizer.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesrandomizer.R
import com.example.seriesrandomizer.api.models.ShowDataClass
import com.example.seriesrandomizer.databinding.PopularShowItemBinding
import com.example.seriesrandomizer.util.ImageLoaderTool

class RecyclerViewAdapter(private val items: MutableList<ShowDataClass>, private val view: View) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PopularShowItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, view)
    }

    fun submitList(newItems: List<ShowDataClass>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: PopularShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShowDataClass, view: View) {
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
                    root.context.getString(
                        R.string.original_language_string,
                        item.originalLanguage?.capitalize()
                    )
                ImageLoaderTool(item.posterPath.orEmpty(), root.context).loadImage(posterImageView)

                clickableArea.setOnClickListener {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_homeFragment_to_showDetailsFragment)
                }
            }
        }
    }
}