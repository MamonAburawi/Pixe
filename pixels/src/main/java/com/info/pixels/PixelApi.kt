package com.info.pixels



import com.info.pixels.data.photo.Curated
import com.info.pixels.data.photo.PhotoSearch
import com.info.pixels.data.video.PopularVideos
import com.info.pixels.data.video.VideoSearch
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


    @GET("videos/popular")
    @Headers("Authorization:$KEY")
    suspend fun getVideos(
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?,
    ): Response<PopularVideos>



    @GET("search")
    @Headers("Authorization:$KEY")
    suspend fun searchPhoto(@Query("query") query: String,
                            @Query("per_page") per_page: Int?,
    ): Response<PhotoSearch>



    @GET("videos/search")
    @Headers("Authorization:$KEY")
    suspend fun searchVideo(@Query("query") query: String,
                            @Query("per_page") per_page: Int?,
    ): Response<VideoSearch>



}