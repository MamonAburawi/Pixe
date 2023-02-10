package com.mamon.pixe.screens.photos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.info.pixels.data.photo.Photo
import com.mamon.pixe.R
import com.mamon.pixe.databinding.PhotosBinding

import com.mamon.pixe.screens.LoadStateViewHolder
import com.mamon.pixe.screens.MyLoadStateAdapter
import com.mamon.pixe.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                if (photos != null){
                    binding.loader.hide()
                    lifecycleScope.launchWhenStarted {
                        photos.collectLatest { data ->
                                MainScope().launch {
                                    binding.rvPhotos.show()
                                }
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





    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error

        errorState?.let {
          showMessage(it.error.toString())
        }
    }


    private fun setAdapter() {
        photoAdapter.listener = object : PhotoAdapter.PhotoListener {
            override fun onClick(photo: Photo) {
                val data = bundleOf(Constants.KEY_Photo_URL to photo.src.large )
                findNavController().navigate(R.id.action_photos_to_photoViewer,data)
            }
        }

        binding.rvPhotos.adapter = photoAdapter.withLoadStateHeaderAndFooter(
            header = MyLoadStateAdapter { photoAdapter.retry() },
            footer = MyLoadStateAdapter { photoAdapter.retry() }
        )


        binding.rvPhotos.adapter = photoAdapter
    }





}