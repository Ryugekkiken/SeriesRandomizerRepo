package com.example.seriesrandomizer.api.responses

import com.example.seriesrandomizer.api.models.CreatedByModel
import com.example.seriesrandomizer.api.models.EpisodeModel
import com.example.seriesrandomizer.api.models.GenresModel
import com.example.seriesrandomizer.api.models.NetworkModel
import com.google.gson.annotations.SerializedName

data class ShowInformationFromIdResponse(
    @SerializedName("name") val showName: String?, 
    @SerializedName("tagline") val tagline: String?, 
    @SerializedName("poster_path") val posterPath: String?, 
    @SerializedName("created_by") val createdBy: List<CreatedByModel>?, 
    @SerializedName("in_production") val inProduction: Boolean?, 
    @SerializedName("number_of_seasons") val numberOfSeasons: Int?, 
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int?, 
    @SerializedName("episode_run_time") val averageEpisodeRuntime: List<Int>?, 
    @SerializedName("languages") val languages: List<String>?, 
    @SerializedName("first_air_date") val firstAirDate: String?, 
    @SerializedName("networks") val whereToWatch: List<NetworkModel>?, 
    @SerializedName("overview") val overview: String?, 
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("genres") val genres: List<GenresModel>?,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: EpisodeModel?,
    )
