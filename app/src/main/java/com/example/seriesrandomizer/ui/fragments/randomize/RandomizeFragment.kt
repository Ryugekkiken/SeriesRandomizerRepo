package com.example.seriesrandomizer.ui.fragments.randomize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.seriesrandomizer.R
import com.example.seriesrandomizer.api.models.EpisodeModel
import com.example.seriesrandomizer.databinding.FragmentRandomizeBinding
import com.example.seriesrandomizer.util.ImageLoaderTool
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random
import kotlin.random.nextInt

@AndroidEntryPoint
class RandomizeFragment : Fragment() {
    private lateinit var binding: FragmentRandomizeBinding

    private val args: RandomizeFragmentArgs by navArgs()
    private val viewModel: RandomizeFragmentViewModel by viewModels()
    private val episodeList: MutableList<EpisodeModel> = mutableListOf()

    private var isFirstCreation: Boolean = true

    companion object {
        const val SPINNER_ANY_SEASON_OPTION = "Any season"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomizeBinding.inflate(inflater, container, false)
        if (isFirstCreation) {
            with(binding) {
                episodeStillView.visibility = View.GONE
                episodeInfo.visibility = View.GONE
            }
        }

        viewModel.getEpisodeList(args.showId)

        viewModel.numberOfSeasons.observe(viewLifecycleOwner) {
            fillSpinnerOptions(it)
        }

        viewModel.episodeList.observe(viewLifecycleOwner) {
            episodeList.addAll(it)
        }

        binding.clickableArea.setOnClickListener {
            handleClickArea()
        }

        return binding.root
    }

    private fun handleClickArea() {
        with(binding) {
            hintView.visibility = View.GONE
            episodeStillView.visibility = View.VISIBLE
            episodeInfo.visibility = View.VISIBLE
        }

        setupViewWithEpisodeInfo()
    }

    private fun setupViewWithEpisodeInfo() {
        val selectedItem = binding.seasonSpinner.selectedItem as String

        val randomizedEpisode = if (selectedItem == SPINNER_ANY_SEASON_OPTION) {
            randomizeFromAnySeason(episodeList)
        } else {
            randomizeFromSpecificSeason(
                episodeList,
                selectedItem.toInt()
            )
        }

        with(binding) {
            ImageLoaderTool(
                randomizedEpisode.episodeStillPath.orEmpty(),
                requireContext()
            ).loadImage(episodeStillView)

            episodeInfo.text = getString(
                R.string.episode_info_string,
                randomizedEpisode.seasonNumber,
                randomizedEpisode.episodeNumber
            )

            episodeName.text = randomizedEpisode.name.orEmpty()
            episodeDescription.text = randomizedEpisode.overview.orEmpty()
        }

    }

    private fun randomizeFromSpecificSeason(
        episodeList: MutableList<EpisodeModel>,
        seasonNumber: Int
    ): EpisodeModel {
        val filteredList = episodeList.filter { it.seasonNumber == seasonNumber }

        return filteredList[Random.nextInt(1 until filteredList.size)]
    }

    private fun randomizeFromAnySeason(episodeList: MutableList<EpisodeModel>): EpisodeModel {
        return episodeList[Random.nextInt(1 until episodeList.size)]
    }

    private fun fillSpinnerOptions(numberOfSeasons: Int) {
        val items: MutableList<String> = mutableListOf()

        items.add(getString(R.string.first_spinner_option))

        for (seasonNumber in 1..numberOfSeasons) {
            items.add(seasonNumber.toString())
        }

        val adapter = ArrayAdapter(
            this.requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            items
        )

        binding.seasonSpinner.adapter = adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        isFirstCreation = true
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        isFirstCreation = true
        super.onDestroy()
    }
}