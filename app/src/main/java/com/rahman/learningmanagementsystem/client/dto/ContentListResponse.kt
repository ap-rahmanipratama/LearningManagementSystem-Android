package com.rahman.learningmanagementsystem.client.dto

import com.squareup.moshi.Json

data class ContentListResponse(
    val title: String,

    @field:Json(name = "presenter_name")
    val presenterName: String,

    val description: String,

    @field:Json(name = "thumbnail_url")
    val thumbnailURL: String,

    @field:Json(name = "video_url")
    val videoURL: String,

    @field:Json(name = "video_duration")
    val videoDuration: Int
)