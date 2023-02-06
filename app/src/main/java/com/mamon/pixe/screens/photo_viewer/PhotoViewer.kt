package com.mamon.pixe.screens.photo_viewer


import android.Manifest.permission.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mamon.pixe.databinding.PhotoViewerBinding
import com.mamon.pixe.utils.Constants
import com.mamon.pixe.utils.FileFormat
import com.mamon.pixe.utils.download

class PhotoViewer : Fragment() {

    private lateinit var binding: PhotoViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = PhotoViewerBinding.inflate(inflater,container,false)


        setViews()


        return binding.root
    }

    private fun setViews() {
        binding.apply {
            photoUri = getPhotoUrl()

            /** button back **/
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            btnDownload.setOnClickListener {
                checkPermission()
            }

        }
    }


    private fun getPhotoUrl() = try {arguments?.getString(Constants.KEY_Photo_URL) ?: ""} catch (ex:Exception){""}



    private fun checkPermission() {
        Dexter.withContext(requireContext() )
            .withPermissions(
                WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {

                    // download file
                    download(getPhotoUrl().toUri(),"downloading file",FileFormat.JPEG.name)

                }
                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {}
            }).check()
    }





}