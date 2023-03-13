package com.example.seriesrandomizer.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriesrandomizer.api.responses.ShowInformationFromIdResponse
import com.example.seriesrandomizer.api.services.SeriesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsFragmentViewModel @Inject constructor(
    private val repository: SeriesApiClient,
    private val apiKey: String
) : ViewModel() {
    private val _showInformation = MutableLiveData<ShowInformationFromIdResponse>()
    val showInformation: LiveData<ShowInformationFromIdResponse> get() = _showInformation

    fun getShowInformationFromId(id: Int) {
        viewModelScope.launch {
            _showInformation.value = repository.getShowInformationFromId(id, apiKey).body()
        }
    }
}