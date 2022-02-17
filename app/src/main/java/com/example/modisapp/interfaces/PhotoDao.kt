package com.example.modisapp.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.modisapp.models.PhotoModel

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotos(photos: List<PhotoModel>)

    @Query("SELECT * FROM photo_table ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN albumId END ASC," +
            "CASE WHEN :isAsc = 0 THEN albumId END DESC")
    suspend fun getPhotos(isAsc: Boolean): List<PhotoModel>
}