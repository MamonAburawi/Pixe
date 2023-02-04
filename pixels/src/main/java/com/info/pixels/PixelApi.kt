package com.info.pixels



import com.info.pixels.data.Curated
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
}