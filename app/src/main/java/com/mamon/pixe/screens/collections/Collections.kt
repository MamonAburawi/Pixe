package com.mamon.pixe.screens.collections


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mamon.pixe.databinding.CollectionsBinding
import com.mamon.pixe.screens.photos.PhotoViewModel

class Collections : Fragment() {

    private lateinit var binding: CollectionsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = CollectionsBinding.inflate(inflater,container,false)






        return binding.root
    }



}