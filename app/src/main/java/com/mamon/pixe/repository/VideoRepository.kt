package com.mamon.pixe.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.info.pixels.PixelApi
import com.info.pixels.data.video.Video
import com.mamon.pixe.screens.videos.VideoDataSource
import com.mamon.pixe.utils.MAX_SIZE
import com.mamon.pixe.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val pixelApi: PixelApi
) {

    fun getVideos(): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE, maxSize = MAX_SIZE),
            pagingSourceFactory = {
                VideoDataSource(query = null, apiService = pixelApi)
            }
        ).flow
    }


    fun search(query: String): Flow<PagingData<Video>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE, maxSize = MAX_SIZE),
            pagingSourceFactory = {
                VideoDataSource(query = query, apiService = pixelApi)
            }
        ).flow
    }


}