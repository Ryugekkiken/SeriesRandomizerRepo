package com.example.seriesrandomizer.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriesrandomizer.api.models.ShowModel
import com.example.seriesrandomizer.api.services.SeriesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: SeriesApiClient,
    private val apiKey: String
) : ViewModel() {
    private val _seriesList = MutableLiveData<List<ShowModel>>()
    val seriesList: LiveData<List<ShowModel>> get() = _seriesList

    fun getSeriesListBySearch(query: String) {
        viewModelScope.launch {
            _seriesList.value = repository.getShowFromSearch(apiKey, query).body()?.results
        }
    }
}