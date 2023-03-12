package com.example.seriesrandomizer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesrandomizer.api.models.ShowDataClass
import com.example.seriesrandomizer.databinding.PopularShowItemBinding

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
            binding.showId.text = item.id.toString()
            binding.showName.text = item.showName
        }
    }
}