package com.mamon.pixe.screens.videos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mamon.pixe.R
import com.mamon.pixe.databinding.VideosBinding
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
class Videos : Fragment() {

    private lateinit var binding: VideosBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = VideosBinding.inflate(inflater,container,false)


        return binding.root

    }


}