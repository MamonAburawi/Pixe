package com.info.pixels.data.video

data class VideoSearch(
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<Video>
)
