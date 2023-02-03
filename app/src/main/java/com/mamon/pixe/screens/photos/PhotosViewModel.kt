package com.mamon.pixe.screens.photos


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mamon.pixe.utils.PAGE_SIZE
import com.mamon.pixe.utils.PREFETCH_DIST
import com.mamon.pixe.PhotoDataSource
import com.mamon.pixe.data.Curated
import com.mamon.pixe.data.Photo
import com.mamon.pixe.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    companion object{
        const val TAG = "PhotoViewModel"
        const val PAGE = PAGE_SIZE
        const val MAX_SIZE = PAGE_SIZE + 2 * PREFETCH_DIST
    }

    private var currentResult: Flow<PagingData<Photo>>? = null


    val listData = Pager(PagingConfig(pageSize = PAGE_SIZE, maxSize = MAX_SIZE)) {
        PhotoDataSource(repository.pixelApi)
    }.flow.cachedIn(viewModelScope)


    val data = repository.getPhotos().cachedIn(viewModelScope)





    @ExperimentalPagingApi
    fun searchPhotos(): Flow<PagingData<Photo>> {
        val newResult = repository.getPhotos().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }


    fun getData(){
        viewModelScope.launch {
            val items = repository.pixelApi.getPhotos(page = 2, per_page = 40)
            Log.d(TAG,"items= ${items.body()?.photos?.first()?.url}")
        }
    }

    @ExperimentalPagingApi
    fun getPhotos()  = Pager(
        config = PagingConfig(100,enablePlaceholders = false),
        pagingSourceFactory = { PhotoDataSource(repository.pixelApi)}
    ).flow.cachedIn(viewModelScope)


    /**
     * Same thing but with Livedata
     */
    private var currentResultLiveData: LiveData<PagingData<Curated>>? = null

//    fun searchPlayersLiveData(): LiveData<PagingData<Photo>> {
//        val newResultLiveData: LiveData<PagingData<Photo>> =
//            repository.getPlayersLiveData().cachedIn(viewModelScope)
//        currentResultLiveData = newResultLiveData
//        return newResultLiveData
//    }


}
