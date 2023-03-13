package com.example.seriesrandomizer.api.responses

import com.example.seriesrandomizer.api.models.CreatedByModel
import com.example.seriesrandomizer.api.models.EpisodeModel
import com.example.seriesrandomizer.api.models.GenresModel
import com.example.seriesrandomizer.api.models.NetworkModel
import com.google.gson.annotations.SerializedName

data class ShowInformationFromIdResponse(
    @SerializedName("name") val showName: String?, //Done
    @SerializedName("tagline") val tagline: String?, //Done
    @SerializedName("poster_path") val posterPath: String?, //Done
    @SerializedName("created_by") val createdBy: List<CreatedByModel>?, //Done
    @SerializedName("in_production") val inProduction: Boolean?, //Done
    @SerializedName("number_of_seasons") val numberOfSeasons: Int?, //Done
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int?, //Done
    @SerializedName("episode_run_time") val averageEpisodeRuntime: List<Int>?, //Done
    @SerializedName("languages") val languages: List<String>?, //Done
    @SerializedName("first_air_date") val firstAirDate: String?, //Done
    @SerializedName("networks") val whereToWatch: List<NetworkModel>?, //Done
    @SerializedName("overview") val overview: String?, //Done
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("genres") val genres: List<GenresModel>?, //Done
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: EpisodeModel?,
    )
