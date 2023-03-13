package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class NetworkModel(
    @SerializedName("logo_path") val logoUrl: String?,
    @SerializedName("name") val networkName: String?
)
