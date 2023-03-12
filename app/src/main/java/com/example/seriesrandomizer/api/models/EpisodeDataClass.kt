package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class EpisodeDataClass(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("air_date") val airDate: String?,
    @SerializedName("episode_number") val episodeNumber: String?
)
