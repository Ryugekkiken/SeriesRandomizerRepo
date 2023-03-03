package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class ShowDataClass(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val showName: String
)
