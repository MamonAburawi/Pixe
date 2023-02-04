package com.info.pixels.data

data class Curated(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>
)