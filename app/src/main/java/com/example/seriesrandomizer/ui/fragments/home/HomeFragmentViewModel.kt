package com.example.seriesrandomizer.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriesrandomizer.api.models.ShowDataClass
import com.example.seriesrandomizer.api.services.SeriesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: SeriesApiClient,
    private val apiKey: String
) : ViewModel() {

    private val _seriesList = MutableLiveData<List<ShowDataClass>>()
    val seriesList: LiveData<List<ShowDataClass>> get() = _seriesList

    fun getSeriesList() {
        viewModelScope.launch {
            _seriesList.value = repository.getPopularSeries(apiKey).body()?.results
        }
    }
}