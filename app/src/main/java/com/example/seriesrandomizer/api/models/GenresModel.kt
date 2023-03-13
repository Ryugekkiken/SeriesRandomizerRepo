package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class GenresModel(
    @SerializedName("name") val genreName: String?
)
