package com.mamon.pixe.screens.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.info.pixels.data.video.Video
import com.mamon.pixe.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {


    private val _videos = MutableLiveData<Flow<PagingData<Video>?>>()
    val videos = _videos


    init {
        getData()
    }


    fun getData() {
        _videos.value = repository.getVideos().cachedIn(viewModelScope)
    }

    fun search(query: String) {
        _videos.value = repository.search(query).cachedIn(viewModelScope)
    }




}