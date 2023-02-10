package com.mamon.pixe.screens.photos


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.info.pixels.data.photo.Photo
import com.mamon.pixe.utils.PER_PAGE
import com.mamon.pixe.utils.PREFETCH_DIST
import com.mamon.pixe.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {



    private val _photos = MutableLiveData<Flow<PagingData<Photo>>?>()
    val photos: LiveData<Flow<PagingData<Photo>>?> = _photos


    init {
        getData()
    }


    fun getData() {
        _photos.value = repository.getPhotos().cachedIn(viewModelScope)
    }

    fun search(query: String) {
        _photos.value = repository.search(query).cachedIn(viewModelScope)
    }




}
