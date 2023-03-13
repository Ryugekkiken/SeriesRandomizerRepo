package com.example.seriesrandomizer.api.models

import com.google.gson.annotations.SerializedName

data class CreatedByModel(
    @SerializedName("name") val creatorName: String?,
    @SerializedName("profile_path") val profilePicturePath: String?
)
