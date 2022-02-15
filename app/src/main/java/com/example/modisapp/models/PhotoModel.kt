package com.example.modisapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PhotoModel(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable
