package com.info.pixels.data.photo

data class PhotoSearch(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>
)
