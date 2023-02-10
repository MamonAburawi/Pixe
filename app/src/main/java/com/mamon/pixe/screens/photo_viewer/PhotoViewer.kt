package com.mamon.pixe.screens.photo_viewer


import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieListener
import com.mamon.pixe.R
import com.mamon.pixe.databinding.PhotoViewerBinding
import com.mamon.pixe.utils.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class PhotoViewer : Fragment() {

    private lateinit var binding: PhotoViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = PhotoViewerBinding.inflate(inflater,container,false)

        setViews()

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        requireActivity().registerReceiver(onDownloadFinished, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }


    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(onDownloadFinished)
    }


    private fun setViews() {
        binding.apply {
            photoUri = getPhotoUrl()

            /** button back **/
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            /** button download **/
            btnDownload.setOnClickListener {
                downloadFile()
            }

            btnScreenCapture.setOnClickListener {
               screenCapture()
            }


        }
    }

    private fun screenCapture(){

        takeScreenShoot(binding.root,"screen shoot")
    }

    private fun getPhotoUrl() = try {arguments?.getString(Constants.KEY_Photo_URL) ?: ""} catch (ex:Exception){""}


    private fun downloadFile(){
        binding.btnDownload.startAnimation(R.raw.download)
        checkPermissions(
            onApprove = {
                download(getPhotoUrl().toUri(),"downloading file",FileFormat.JPEG.name)
            },
            onDecline = {
                binding.btnDownload.stopAnimation()
            }
        )
    }




    private val onDownloadFinished: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            binding.btnDownload.stopAnimation()
        }
    }





}