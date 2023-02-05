package com.mamon.pixe.screens.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.info.pixels.data.Photo
import com.mamon.pixe.R
import com.mamon.pixe.databinding.PhotosBinding
import com.mamon.pixe.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class Photos : Fragment() {

    private lateinit var binding: PhotosBinding
    private val viewModel by activityViewModels<PhotoViewModel>()
    private val photoAdapter by lazy { PhotoAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = PhotosBinding.inflate(inflater,container,false)


        setViews()
        setObserves()



        return binding.root
    }




    private fun setObserves() {
        viewModel.apply {



            // photos
            photos.observe(viewLifecycleOwner){ photos ->
                lifecycleScope.launchWhenStarted {
                    photos.collectLatest { data ->
                        if (data != null) {
                            photoAdapter.submitData(data)
                        }
                    }
                }
            }



        }
    }


    private fun setViews() {
        binding.apply {

            setAdapter()

            appBar.searchField.hint = getString(R.string.info_app_bar_photo)

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


    fun setAdapter(){
        photoAdapter.listener = object : PhotoAdapter.PhotoListener{
            override fun onClick(data: Photo) {
                Toast.makeText(requireContext(),data.url,Toast.LENGTH_SHORT).show()
            }
        }
        binding.rvPhotos.adapter = photoAdapter
    }




}