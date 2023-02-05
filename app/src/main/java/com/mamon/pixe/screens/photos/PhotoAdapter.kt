package com.mamon.pixe.screens.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.pixels.data.Photo
import com.mamon.pixe.databinding.ItemPhotoBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


class PhotoAdapter: PagingDataAdapter<Photo, PhotoAdapter.NoteViewHolder>(NoteDiffUtil()) {

    lateinit var listener: PhotoListener





    inner class NoteViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Photo){
            binding.photo = data
            binding.item.setOnClickListener {
                listener.onClick(data)
            }

        }
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)!!
        holder.bind(note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater,parent,false)
        return NoteViewHolder(binding)
    }


    class NoteDiffUtil : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem
    }



    interface PhotoListener {
        fun onClick(data: Photo)
    }


}
