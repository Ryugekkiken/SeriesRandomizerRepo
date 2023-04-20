package com.example.seriesrandomizer.api.responses

import com.example.seriesrandomizer.api.models.EpisodeModel
import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("episodes")
    val episodeList: List<EpisodeModel>?
)
