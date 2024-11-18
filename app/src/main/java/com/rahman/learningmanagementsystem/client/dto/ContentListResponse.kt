package com.rahman.learningmanagementsystem.client.dto

import com.rahman.learningmanagementsystem.ui.viewdata.ContentViewData
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

fun ContentListResponse.toContentViewData(): ContentViewData {
    return ContentViewData(
        title = this.title,
        presenterName = this.presenterName,
        description = this.description,
        thumbnailURL = this.thumbnailURL,
        videoURL = this.videoURL,
        videoDuration = formatDuration(this.videoDuration)
    )
}

fun formatDuration(duration: Int): String {
    val minutes = duration / 1000 / 60
    val remainingSeconds = duration / 1000 % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}