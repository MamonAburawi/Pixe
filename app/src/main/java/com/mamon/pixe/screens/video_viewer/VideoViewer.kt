package com.mamon.pixe.screens.video_viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.mamon.pixe.databinding.VideoViewerBinding
import com.mamon.pixe.utils.Constants


class VideoViewer : Fragment() {

    private lateinit var binding: VideoViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideoViewerBinding.inflate(inflater,container,false)


        setViews()


        initVideo()

        return binding.root
    }

    private fun setViews() {
        binding.apply {

            // back button
            btnBack.setOnClickListener { findNavController().navigateUp() }

        }
    }

    private fun initVideo() {
        val player = ExoPlayer.Builder(requireContext()).build()
        binding.viewer.player = player
        val mediaItem = MediaItem.fromUri(getVideoUrl())
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }


   private fun getVideoUrl() = try {arguments?.getString(Constants.KEY_VIDEO_URL) ?: ""} catch (ex:Exception){""}

}