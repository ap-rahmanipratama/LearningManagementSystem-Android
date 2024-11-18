package com.rahman.learningmanagementsystem.client.dto

import com.squareup.moshi.Json

data class ContentListResponse(
    val title: String,

    @Json(name = "presenter_name")
    val presenterName: String,

    val description: String,

    @Json(name = "thumbnail_url")
    val thumbnailURL: String,

    @Json(name = "video_url")
    val videoURL: String,

    @Json(name = "video_duration")
    val videoDuration: Int
)