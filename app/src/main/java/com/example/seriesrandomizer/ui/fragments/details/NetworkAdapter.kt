package com.example.seriesrandomizer.ui.fragments.details

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesrandomizer.api.models.CreatedByModel
import com.example.seriesrandomizer.api.models.NetworkModel
import com.example.seriesrandomizer.databinding.NetworkItemBinding
import com.example.seriesrandomizer.util.ImageLoaderTool

class NetworkAdapter(private val items: MutableList<NetworkModel>) :
    RecyclerView.Adapter<NetworkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NetworkItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<NetworkModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: NetworkItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkModel) {
            binding.networkName.text = item.networkName
        }
    }
}