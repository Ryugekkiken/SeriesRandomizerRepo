package com.example.seriesrandomizer.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seriesrandomizer.R
import com.example.seriesrandomizer.api.responses.ShowInformationFromIdResponse
import com.example.seriesrandomizer.databinding.FragmentShowDetailsBinding
import com.example.seriesrandomizer.util.ImageLoaderTool
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsFragment : Fragment() {
    lateinit var binding: FragmentShowDetailsBinding

    private val args: ShowDetailsFragmentArgs by navArgs()
    private val viewModel: ShowDetailsFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        viewModel.getShowInformationFromId(args.showId)

        viewModel.showInformation.observe(viewLifecycleOwner) {
            buildViewFromResponse(it)
        }
        return binding.root
    }

    private fun buildViewFromResponse(response: ShowInformationFromIdResponse) {
        with(binding) {
            val createdByAdapter = CreatedByAdapter(mutableListOf())
            val networkAdapter = NetworkAdapter(mutableListOf())

            recyclerViewNetworks.adapter = networkAdapter
            recyclerViewCreatedBy.adapter = createdByAdapter

            if (!response.createdBy.isNullOrEmpty()) {
                createdByAdapter.submitList(response.createdBy)
            } else {
                createdByText.visibility = View.GONE
                createdBySection.visibility = View.GONE
            }

            if (!response.whereToWatch.isNullOrEmpty()) {
                networkAdapter.submitList(response.whereToWatch)
            } else {
                whereToWatchSection.visibility = View.GONE
                whereToWatchText.visibility = View.GONE
            }

            recyclerViewCreatedBy.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerViewNetworks.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            response.posterPath?.let { ImageLoaderTool(it, requireContext()).loadImage(showBanner) }
            showName.text = response.showName
            tagline.text = response.tagline
            inProduction.text = if (response.inProduction == true) {
                root.context.getString(R.string.is_in_production_text)
                    .plus(" ")
                    .plus(root.context.getString(R.string.is_in_production_true_text))
            } else {
                root.context.getString(R.string.is_in_production_text)
                    .plus(" ")
                    .plus(root.context.getString(R.string.is_in_production_false_text))
            }

            numberOfSeasons.text = root.context.getString(
                R.string.number_of_seasons_string,
                response.numberOfSeasons.toString()
            )
            numberOfEpisodes.text = root.context.getString(
                R.string.number_of_episodes_string,
                response.numberOfEpisodes.toString()
            )
            if (!response.averageEpisodeRuntime.isNullOrEmpty()) {
                avgEpisodeRuntime.text = root.context.getString(
                    R.string.avg_episode_runtime_string,
                    response.averageEpisodeRuntime.first().toString()
                )
            } else {
                avgEpisodeRuntime.visibility = View.GONE
            }

            if (!response.languages.isNullOrEmpty()) {
                languages.text = root.context.getString(
                    R.string.languages_string,
                    response.languages.first()
                )
            } else {
                languages.visibility = View.GONE
            }

            airDate.text = root.context.getString(
                R.string.air_date_string,
                response.firstAirDate
            )

            if (!response.overview.isNullOrEmpty()) {
                overviewSectionText.text = response.overview
            } else {
                overviewSection.visibility = View.GONE
            }

            if (!response.genres.isNullOrEmpty()) {
                genre.text = root.context.getString(
                    R.string.genre_string,
                    response.genres.first().genreName
                )
            } else {
                genre.visibility = View.GONE
            }

            if (response.lastEpisodeToAir != null) {
                lastEpisodeToAirName.text = response.lastEpisodeToAir.name
                response.lastEpisodeToAir.episodeStillPath?.let {
                    ImageLoaderTool(it, root.context).loadImage(
                        episodeBanner
                    )
                }
                lastEpisodeToAirAirDate.text = root.context.getString(
                    R.string.last_episode_to_air_string,
                    "air date: ",
                    response.lastEpisodeToAir.airDate
                )
                lastEpisodeToAirNumber.text = root.context.getString(
                    R.string.last_episode_to_air_string,
                    "number: ",
                    response.lastEpisodeToAir.episodeNumber
                )
                lastEpisodeToAirRuntime.text = root.context.getString(
                    R.string.last_episode_to_air_string,
                    "runtime: ",
                    response.lastEpisodeToAir.runtime.toString()
                )
                lastEpisodeToAirOverview.text = response.lastEpisodeToAir.overview
            } else {
                lastEpisodeToAirSection.visibility = View.GONE
            }
        }
    }
}