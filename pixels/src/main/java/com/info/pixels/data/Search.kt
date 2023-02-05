package com.info.pixels.data

data class Search(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>
)
