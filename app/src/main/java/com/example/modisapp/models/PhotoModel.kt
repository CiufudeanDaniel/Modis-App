package com.example.modisapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "photo_table")
data class PhotoModel(
    val albumId: Long,
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable
