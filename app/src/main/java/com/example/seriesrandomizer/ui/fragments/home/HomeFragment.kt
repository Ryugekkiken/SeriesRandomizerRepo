package com.example.seriesrandomizer.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seriesrandomizer.databinding.FragmentHomeBinding
import com.example.seriesrandomizer.util.RecyclerViewItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.getSeriesList()

        val adapter = ShowRecyclerViewAdapter(mutableListOf(), binding.root)
        binding.recyclerView.addItemDecoration(RecyclerViewItemDecoration())
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        viewModel.seriesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}