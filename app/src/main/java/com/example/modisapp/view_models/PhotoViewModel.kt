package com.example.modisapp.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.repository.PhotoRepository
import com.example.modisapp.service.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "PhotoViewModel"
class PhotoViewModel(application: Application) : AndroidViewModel(application) {

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
        Log.v(TAG, "getPhotos")
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