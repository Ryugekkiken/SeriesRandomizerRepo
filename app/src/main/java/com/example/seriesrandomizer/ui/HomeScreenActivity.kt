package com.example.seriesrandomizer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seriesrandomizer.databinding.ActivityMainBinding
import com.example.seriesrandomizer.util.RecyclerViewItemDecoration
import dagger.hilt.android.AndroidEntryPoint

//HiltViewModel
@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getSeriesList()

        val adapter = RecyclerViewAdapter(mutableListOf())

        binding.recyclerView.addItemDecoration(RecyclerViewItemDecoration())

        viewModel.seriesList.observe(this) {
            adapter.submitList(it)
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}