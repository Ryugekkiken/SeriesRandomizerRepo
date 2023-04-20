package com.example.seriesrandomizer.ui.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seriesrandomizer.R
import com.example.seriesrandomizer.databinding.FragmentSearchBinding
import com.example.seriesrandomizer.util.RecyclerViewItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        with(binding) {
            val adapter = SearchRecyclerViewAdapter(mutableListOf(), root)
            recyclerViewSearch.addItemDecoration(RecyclerViewItemDecoration())
            recyclerViewSearch.layoutManager =
                LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
            recyclerViewSearch.adapter = adapter

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    viewModel.getSeriesListBySearch(s.toString())
                }

            }

            searchBar.addTextChangedListener(textWatcher)

            viewModel.seriesList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        return binding.root
    }

}