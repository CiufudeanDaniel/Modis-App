package com.example.modisapp.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.modisapp.models.PhotoModel

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhoto(photo: PhotoModel)

    @Query("SELECT * FROM photo_table ORDER BY albumId ASC")
    suspend fun getPhotos(): List<PhotoModel>
}