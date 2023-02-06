package com.mamon.pixe.screens.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.pixels.data.photo.Photo
import com.info.pixels.data.video.Video
import com.mamon.pixe.databinding.ItemPhotoBinding
import com.mamon.pixe.databinding.ItemVideoBinding


class VideoAdapter: PagingDataAdapter<Video, VideoAdapter.NoteViewHolder>(NoteDiffUtil()) {

    lateinit var listener: VideoListener


    inner class NoteViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Video){
            binding.video = data
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
        val binding = ItemVideoBinding.inflate(inflater,parent,false)
        return NoteViewHolder(binding)
    }


    class NoteDiffUtil : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
    }



    interface VideoListener {
        fun onClick(video: Video)
    }


}
