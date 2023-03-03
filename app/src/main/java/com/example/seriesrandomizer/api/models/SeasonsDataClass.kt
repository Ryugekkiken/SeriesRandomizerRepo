package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class SeasonsDataClass(
    @SerializedName("episode_count") val episodeCount: Int,
    @SerializedName("season_number") val seasonNumber: Int,
    @SerializedName("overview") val seasonOverview: String
)
