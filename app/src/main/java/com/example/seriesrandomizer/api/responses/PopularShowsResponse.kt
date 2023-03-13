package com.example.seriesrandomizer.api.responses

import com.example.seriesrandomizer.api.models.ShowModel
import com.google.gson.annotations.SerializedName

data class PopularShowsResponse(
    @SerializedName("results") val results: List<ShowModel>
)
