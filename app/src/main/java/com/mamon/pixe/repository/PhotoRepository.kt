package com.mamon.pixe.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mamon.pixe.utils.PAGE_SIZE
import com.mamon.pixe.PhotoDataSource
import com.mamon.pixe.PixelApi
import com.mamon.pixe.data.Photo
import com.mamon.pixe.utils.MAX_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PhotoRepository @Inject constructor(
    val pixelApi: PixelApi) {


    fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE, maxSize = MAX_SIZE),
            pagingSourceFactory = {
                PhotoDataSource(pixelApi)
            }
        ).flow
    }



}