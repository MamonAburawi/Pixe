package com.mamon.pixe.screens.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.mamon.pixe.data.Photo
import com.mamon.pixe.databinding.PhotosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class Photos : Fragment() {

    private lateinit var binding: PhotosBinding
    private val viewModel by activityViewModels<PhotosViewModel>()
    private val photoAdapter by lazy { PhotoAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = PhotosBinding.inflate(inflater,container,false)


        setAdapter()


        lifecycleScope.launchWhenStarted {
            viewModel.data.collectLatest { data ->
                photoAdapter.submitData(data)
            }
        }



        return binding.root
    }


    fun setAdapter(){
        photoAdapter.listener = object : PhotoAdapter.PhotoListener{
            override fun onClick(data: Photo) {
                Toast.makeText(requireContext(),data.url,Toast.LENGTH_SHORT).show()
            }
        }
        binding.rvPhotos.adapter = photoAdapter
    }




}