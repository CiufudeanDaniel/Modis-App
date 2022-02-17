package com.example.modisapp.repository

import com.example.modisapp.interfaces.PhotoDao
import com.example.modisapp.interfaces.RetrofitAPI
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.service.RetrofitClientInstance

class PhotoRepository(private val photoDao: PhotoDao) {
    private val retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitAPI::class.java)

    suspend fun getPhotos(isAsc: Boolean) : ArrayList<PhotoModel> {
        return try {
            retrofitService.getPhotos()
        } catch (e: Exception) {
            getPhotosFromDB(isAsc)
        }
    }

    suspend fun getPhotosFromDB(isAsc: Boolean) : ArrayList<PhotoModel> {
        return ArrayList(photoDao.getPhotos(isAsc))
    }

    suspend fun addPhotos(photos: List<PhotoModel>) {
        photoDao.addPhotos(photos)
    }
}