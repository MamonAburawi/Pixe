package com.mamon.pixe.screens.photos


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.info.pixels.data.photo.Photo
import com.mamon.pixe.utils.PAGE_SIZE
import com.mamon.pixe.utils.PREFETCH_DIST
import com.mamon.pixe.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {


    companion object {
        const val TAG = "PhotoViewModel"
        const val PAGE = PAGE_SIZE
        const val MAX_SIZE = PAGE_SIZE + 2 * PREFETCH_DIST
    }


    private val _photos = MutableLiveData<Flow<PagingData<Photo>?>>()
    val photos = _photos


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
