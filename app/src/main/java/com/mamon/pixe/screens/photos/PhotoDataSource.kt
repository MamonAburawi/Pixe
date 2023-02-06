package com.mamon.pixe.screens.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.info.pixels.PixelApi
import com.info.pixels.data.photo.Photo
import com.mamon.pixe.utils.PER_PAGE
import com.mamon.pixe.utils.STARTING_PAGE_INDEX


class PhotoDataSource (
    private val query: String?,
    private val apiService: PixelApi
) : PagingSource<Int, Photo>() {


    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Photo> {

        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val data: List<Photo> = when (query){ // user searching
                null ->{ // just fetch the data
                    val response = apiService.getPhotos(per_page = PER_PAGE, page = currentPage)
                      response.body()?.photos ?: emptyList()
                }
                else -> { // user searching
                    val response = apiService.searchPhoto(query = query, per_page = PER_PAGE)
                    response.body()?.photos ?: emptyList()
                }
            }
            val responseData = mutableListOf<Photo>()
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


    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey
        }
    }

}



