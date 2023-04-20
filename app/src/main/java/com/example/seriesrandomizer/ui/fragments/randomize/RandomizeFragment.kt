package com.example.seriesrandomizer.ui.fragments.randomize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.seriesrandomizer.R
import com.example.seriesrandomizer.databinding.FragmentRandomizeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomizeFragment : Fragment() {
    private lateinit var binding: FragmentRandomizeBinding

    private val args: RandomizeFragmentArgs by navArgs()
    private val viewModel: RandomizeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomizeBinding.inflate(inflater, container, false)

        viewModel.getEpisodeList(args.showId)

        viewModel.numberOfSeasons.observe(viewLifecycleOwner) {
            fillSpinnerOptions(it)
        }

        return binding.root
    }


    private fun fillSpinnerOptions(numberOfSeasons: Int) {
        val items: MutableList<String> = mutableListOf()

        items.add(getString(R.string.first_spinner_option))

        for(seasonNumber in 1..numberOfSeasons) {
            items.add(seasonNumber.toString())
        }
    }
}