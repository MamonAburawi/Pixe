package com.mamon.pixe

import com.mamon.pixe.data.Curated
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PixelApi {
    @GET("curated")
    @Headers("Authorization:6aFQf9CX8GsRXYj3t9fbHPUSrPv0rz1wGo69kGDOzkDDIEFfKTferQH9")
    suspend fun getPhotos(
    @Query("per_page") per_page: Int?,
    @Query("page") page: Int?,
    ): Response<Curated>
}