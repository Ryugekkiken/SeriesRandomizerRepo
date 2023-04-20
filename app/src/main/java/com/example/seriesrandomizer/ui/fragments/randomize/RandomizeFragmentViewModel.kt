package com.example.seriesrandomizer.ui.fragments.randomize

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriesrandomizer.api.models.EpisodeModel
import com.example.seriesrandomizer.api.responses.ShowInformationFromIdResponse
import com.example.seriesrandomizer.api.services.SeriesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RandomizeFragmentViewModel @Inject constructor(
    private val repository: SeriesApiClient,
    private val apiKey: String
) : ViewModel() {
    private val _episodeList = MutableLiveData<MutableList<EpisodeModel>>()
    val episodeList: LiveData<MutableList<EpisodeModel>> get() = _episodeList

    private val _numberOfSeasons = MutableLiveData<Int>()
    val numberOfSeasons: LiveData<Int> get() = _numberOfSeasons

    fun getEpisodeList(id: Int) {
        viewModelScope.launch {
            val numberOfSeasons = withContext(Dispatchers.IO) {
                repository.getShowInformationFromId(id, apiKey).body()?.numberOfSeasons
            }

            _numberOfSeasons.value = numberOfSeasons!!

            for (seasonNumber in 1..numberOfSeasons) {
                repository.getEpisodesForSeason(id, seasonNumber, apiKey).body()?.let {
                    it.episodeList?.let { response ->
                        _episodeList.value?.addAll(
                            response.toMutableList()
                        )
                    }
                }
            }
        }
    }
}