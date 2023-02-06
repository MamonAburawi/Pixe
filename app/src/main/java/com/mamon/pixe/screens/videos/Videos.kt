package com.mamon.pixe.screens.videos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.info.pixels.data.photo.Photo
import com.info.pixels.data.video.Video
import com.mamon.pixe.R
import com.mamon.pixe.databinding.VideosBinding
import com.mamon.pixe.screens.photos.PhotoAdapter
import com.mamon.pixe.screens.photos.PhotoViewModel
import com.mamon.pixe.utils.Constants
import com.mamon.pixe.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class Videos : Fragment() {

    private lateinit var binding: VideosBinding
    private val viewModel by activityViewModels<VideosViewModel>()

    private val videoAdapter by lazy { VideoAdapter() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = VideosBinding.inflate(inflater,container,false)

        setViews()

        setObserves()



        return binding.root

    }

    private fun setViews() {
        binding.apply {

            setAdapter()

            appBar.searchField.hint = getString(R.string.info_app_bar_videos)

            /** search app Bar **/
            appBar.searchField.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val query = textView.text.trim().toString()
                    if (query.isNotEmpty()){
                        viewModel.search(query)
                    }else{
                        viewModel.getData()
                    }
                    hideKeyboard()
                    true
                } else { false }
            }

        }

    }


    private fun setObserves() {
        viewModel.apply {


            // videos
            videos.observe(viewLifecycleOwner){ videos ->
                lifecycleScope.launchWhenStarted {
                    videos.collectLatest { data ->
                        if (data != null) {
                            videoAdapter.submitData(data)
                        }
                    }
                }
            }



        }
    }



    private fun setAdapter(){
        videoAdapter.listener = object : VideoAdapter.VideoListener{
            override fun onClick(video: Video) {
                val data = bundleOf(Constants.KEY_VIDEO_URL to video.video_files.first().link)
                findNavController().navigate(R.id.action_videos_to_videoViewer,data)
            }
        }
        binding.rvVideos.adapter = videoAdapter
    }





}