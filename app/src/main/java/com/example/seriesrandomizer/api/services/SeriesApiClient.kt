package com.example.seriesrandomizer.api.services

import com.example.seriesrandomizer.api.models.EpisodeModel
import com.example.seriesrandomizer.api.responses.EpisodeResponse
import com.example.seriesrandomizer.api.responses.PopularShowsResponse
import com.example.seriesrandomizer.api.responses.ShowFromSearchResponse
import com.example.seriesrandomizer.api.responses.ShowInformationFromIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiClient {

    companion object {
        const val POPULAR_TV_SHOWS = "tv/popular"
        const val ALL_EPISODES_FOR_SEASON_ENDPOINT = "tv/{series_id}/season/{season_number}"
        const val SHOW_FROM_SEARCH_ENDPOINT = "search/tv"
        const val SHOW_INFORMATION_FROM_ID = "tv/{tv_id}"
    }

    @GET(POPULAR_TV_SHOWS)
    suspend fun getPopularSeries(@Query("api_key") apiKey: String): Response<PopularShowsResponse>

    @GET(ALL_EPISODES_FOR_SEASON_ENDPOINT)
    suspend fun getEpisodesForSeason(
        @Path("series_id") seriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String
    ): Response<EpisodeResponse>

    @GET(SHOW_FROM_SEARCH_ENDPOINT)
    suspend fun getShowFromSearch(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<ShowFromSearchResponse>

    @GET(SHOW_INFORMATION_FROM_ID)
    suspend fun getShowInformationFromId(
        @Path("tv_id") seriesId: Int,
        @Query("api_key") apiKey: String
    ): Response<ShowInformationFromIdResponse>
}