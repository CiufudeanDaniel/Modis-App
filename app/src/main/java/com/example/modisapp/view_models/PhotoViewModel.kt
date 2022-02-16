package com.example.modisapp.view_models

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.repository.PhotoRepository
import com.example.modisapp.service.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel(application: Application) : ViewModel() {
    private val repository: PhotoRepository
    val photos: MutableLiveData<ArrayList<PhotoModel>> by lazy {
        MutableLiveData<ArrayList<PhotoModel>>().also {
            getPhotos()
        }
    }

    init {
        val photoDao = AppDatabase.getDatabase(application).photoDao()
        repository = PhotoRepository(photoDao)
    }

    private fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPhotos()
            withContext(Dispatchers.Main) {
                photos.value = result
            }
        }
    }

    fun addPhoto(photoModel: PhotoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPhoto(photoModel)
        }
    }
}