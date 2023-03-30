package com.example.seriesrandomizer.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seriesrandomizer.api.models.ShowModel
import com.example.seriesrandomizer.api.services.SeriesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: SeriesApiClient,
    private val apiKey: String
) : ViewModel() {
    private val _seriesList = MutableLiveData<List<ShowModel>>()
    val seriesList: LiveData<List<ShowModel>> get() = _seriesList


}