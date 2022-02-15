package com.example.modisapp.interfaces

import com.example.modisapp.models.PhotoModel
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("photos")
    suspend fun getPhotos(): ArrayList<PhotoModel>
}