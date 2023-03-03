package com.example.seriesrandomizer.api.responses

import com.example.seriesrandomizer.api.models.SeasonsDataClass
import com.google.gson.annotations.SerializedName

data class SeasonsResponse(
    @SerializedName("seasons") val seasons: List<SeasonsDataClass>
)
