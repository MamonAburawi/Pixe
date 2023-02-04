package com.mamon.pixe.screens.videos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.mamon.pixe.R
import com.mamon.pixe.databinding.VideosBinding
import com.mamon.pixe.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
class Videos : Fragment() {

    private lateinit var binding: VideosBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = VideosBinding.inflate(inflater,container,false)

        setViews()

        return binding.root

    }

    private fun setViews() {
        binding.apply {


            appBar.searchField.hint = getString(R.string.info_app_bar_videos)

            /** search app Bar **/
            appBar.searchField.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val query = textView.text.trim().toString()
                    hideKeyboard()
                    true
                } else { false }
            }


        }


    }



}