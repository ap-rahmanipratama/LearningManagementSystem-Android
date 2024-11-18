package com.rahman.learningmanagementsystem.ui.viewdata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentViewData(
    val title: String,
    val presenterName: String,
    val description: String,
    val thumbnailURL: String,
    val videoURL: String,
    val videoDuration: String
) : Parcelable