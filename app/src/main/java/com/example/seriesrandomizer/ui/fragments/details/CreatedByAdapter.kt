package com.example.seriesrandomizer.ui.fragments.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesrandomizer.api.models.CreatedByModel
import com.example.seriesrandomizer.databinding.CreatedByItemBinding
import com.example.seriesrandomizer.util.ImageLoaderTool

class CreatedByAdapter(private val items: MutableList<CreatedByModel>) :
    RecyclerView.Adapter<CreatedByAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedByAdapter.ViewHolder {
        return ViewHolder(CreatedByItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CreatedByAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<CreatedByModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CreatedByItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CreatedByModel) {
            with(binding) {
                item.profilePicturePath?.let {
                    ImageLoaderTool(it, binding.root.context).loadImage(
                        creatorProfilePicture
                    )
                }
                creatorName.text = item.creatorName
            }
        }
    }
}