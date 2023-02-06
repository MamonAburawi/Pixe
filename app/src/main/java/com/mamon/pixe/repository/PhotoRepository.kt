package com.mamon.pixe.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.info.pixels.PixelApi
import com.info.pixels.data.photo.Photo
import com.mamon.pixe.screens.photos.PhotoDataSource
import com.mamon.pixe.utils.PER_PAGE
import com.mamon.pixe.utils.MAX_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val pixelApi: PixelApi) {

    fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PER_PAGE),
            pagingSourceFactory = {
                PhotoDataSource(query = null,pixelApi)
            }
        ).flow
    }

     fun search(query: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PER_PAGE),
            pagingSourceFactory = {
                PhotoDataSource(query,pixelApi)
            }
        ).flow
    }


}