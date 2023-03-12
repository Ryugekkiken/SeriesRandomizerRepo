package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class ShowDataClass(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val showName: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("original_language") val originalLanguage: String?
)
