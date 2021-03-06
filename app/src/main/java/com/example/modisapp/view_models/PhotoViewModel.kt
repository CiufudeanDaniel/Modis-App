package com.example.modisapp.view_models

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.repository.PhotoRepository
import com.example.modisapp.service.AppDatabase
import kotlinx.coroutines.*

private const val TAG = "PhotoViewModel"
class PhotoViewModel(application: Application) : AndroidViewModel(application) {
    private var job: Job? = null
    val isLoading = ObservableBoolean()

    private val repository: PhotoRepository
    val photos: MutableLiveData<ArrayList<PhotoModel>> by lazy {
        MutableLiveData<ArrayList<PhotoModel>>().also {
            getPhotos(true)
        }
    }

    init {
        val photoDao = AppDatabase.getDatabase(application).photoDao()
        repository = PhotoRepository(photoDao)
    }

    fun getPhotos(isAsc: Boolean) {
        Log.v(TAG, "getPhotos")
        isLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPhotos(isAsc)
            withContext(Dispatchers.Main) {
                photos.value = result
                isLoading.set(false)
            }
        }
    }

    fun getPhotosFromDB(isAsc: Boolean) {
        Log.v(TAG, "getPhotos from DB")
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPhotosFromDB(isAsc)
            withContext(Dispatchers.Main) {
                photos.value = result
            }
        }
    }

    fun addPhotos(photoModel: List<PhotoModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPhotos(photoModel)
        }
    }
}