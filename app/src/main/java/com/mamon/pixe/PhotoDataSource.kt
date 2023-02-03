package com.mamon.pixe

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mamon.pixe.data.Photo
import com.mamon.pixe.utils.STARTING_PAGE_INDEX


class PhotoDataSource(
    private val apiService: PixelApi
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Photo> {

        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getPhotos(per_page = 10, page = currentPage)
            val responseData = mutableListOf<Photo>()
            val data = response.body()?.photos ?: emptyList()



            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    //todo: when scroll up the app crash the issue from refresh key.
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return null
    }


}

