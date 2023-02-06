package com.mamon.pixe.screens.videos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.info.pixels.PixelApi
import com.info.pixels.data.video.Video

import com.mamon.pixe.utils.PER_PAGE
import com.mamon.pixe.utils.STARTING_PAGE_INDEX

class VideoDataSource (
    private val query: String?,
    private val apiService: PixelApi
) : PagingSource<Int, Video>() {

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Video> {

        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val data: List<Video> = when (query){ // user searching
                null ->{ // just fetch the data
                    val response = apiService.getVideos(per_page = PER_PAGE, page = currentPage)
                    response.body()?.videos ?: emptyList()
                }
                else -> { // user searching
                    val response = apiService.searchVideo(query = query, per_page = PER_PAGE)
                    response.body()?.videos ?: emptyList()
                }
            }
            val responseData = mutableListOf<Video>()
            responseData.addAll(data)

            LoadResult.Page(
                data = data ,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, Video>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey
        }
    }

}


