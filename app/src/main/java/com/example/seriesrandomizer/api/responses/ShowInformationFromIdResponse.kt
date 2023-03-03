package com.example.seriesrandomizer.api.responses

import com.example.seriesrandomizer.api.models.NetworkDataClass
import com.google.gson.annotations.SerializedName

data class ShowInformationFromIdResponse(
    @SerializedName("episode_run_time") val episodeRuntime: Int,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("networks") val whereToWatch: List<NetworkDataClass>,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("in_production") val inProduction: Boolean
)
