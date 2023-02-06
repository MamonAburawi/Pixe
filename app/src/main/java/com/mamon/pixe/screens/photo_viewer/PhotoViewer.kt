package com.mamon.pixe.screens.photo_viewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mamon.pixe.R
import com.mamon.pixe.databinding.PhotoViewerBinding
import com.mamon.pixe.utils.Constants


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

        }
    }


    private fun getPhotoUrl() = try {arguments?.getString(Constants.KEY_Photo_URL) ?: ""} catch (ex:Exception){""}

}