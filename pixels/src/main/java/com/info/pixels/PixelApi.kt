package com.info.pixels



import com.info.pixels.data.Curated
import com.info.pixels.data.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface PixelApi {

    @GET("curated")
    @Headers("Authorization:$KEY")
    suspend fun getPhotos(
    @Query("per_page") per_page: Int?,
    @Query("page") page: Int?,
    ): Response<Curated>


    @GET("search")
    @Headers("Authorization:$KEY")
    suspend fun search(@Query("query") query: String,
                       @Query("per_page") per_page: Int?,
    ): Response<Search>


//    @Headers("Authorization:$KEY")
//    @GET("search")
//    fun getPexelsImage(@Query("query") query: String): Single<PexelsImageWrapper>


//    https://api.pexels.com/v1/search?query=nature&per_page=1

}