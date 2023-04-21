package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("air_date") val airDate: String?,
    @SerializedName("episode_number") val episodeNumber: Int?,
    @SerializedName("season_number") val seasonNumber: Int?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("still_path") val episodeStillPath: String?,

)
