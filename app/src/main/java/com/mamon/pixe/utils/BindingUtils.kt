package com.mamon.pixe.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mamon.pixe.R


@BindingAdapter("setImage")
fun setImage(view: ImageView, imageUri: String) =
    Glide.with(view.context)
        .load(imageUri)
        .error(R.drawable.ic_broken_image)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(view)
