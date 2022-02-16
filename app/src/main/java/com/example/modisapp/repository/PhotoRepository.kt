package com.example.modisapp.repository

import com.example.modisapp.interfaces.RetrofitAPI
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.service.RetrofitClientInstance

class PhotoRepository {
    private val retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitAPI::class.java)

    suspend fun getPhotos() : ArrayList<PhotoModel> {
        return try {
            retrofitService.getPhotos()
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}