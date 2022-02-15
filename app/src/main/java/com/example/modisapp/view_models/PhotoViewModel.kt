package com.example.modisapp.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel : ViewModel() {
    private val repository = PhotoRepository()
    val photos: MutableLiveData<ArrayList<PhotoModel>> by lazy {
        MutableLiveData<ArrayList<PhotoModel>>().also {
            getPhotos()
        }
    }

    private fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPhotos()
            withContext(Dispatchers.Main) {
                photos.value = result
            }
        }
    }
}