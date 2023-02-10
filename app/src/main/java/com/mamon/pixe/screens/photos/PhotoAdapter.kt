
package com.mamon.pixe.screens.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.pixels.data.photo.Photo
import com.mamon.pixe.databinding.ItemLoadStateBinding
import com.mamon.pixe.databinding.ItemPhotoBinding



class PhotoAdapter: PagingDataAdapter<Photo, PhotoAdapter.PhotoViewHolder>(NoteDiffUtil()) {

    lateinit var listener: PhotoListener

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1


    inner class PhotoViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Photo){
            binding.photo = data
            binding.item.setOnClickListener {
                listener.onClick(data)
            }

        }
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val note = getItem(position)!!
        holder.bind(note)
    }



//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.PhotoViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        if (viewType == VIEW_TYPE_ITEM) {
//            val binding = ItemPhotoBinding.inflate(inflater,parent,false)
//            PhotoViewHolder(binding)
//        } else {
//            val binding = ItemLoadStateBinding.inflate(inflater,parent,false)
//            PhotoViewHolder(binding)
//        }
//
//
//
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater,parent,false)
        return PhotoViewHolder(binding)
    }


    class NoteDiffUtil : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem
    }



    interface PhotoListener {
        fun onClick(photo: Photo)
    }


}

//
//class PhotoAdapter: PagingDataAdapter<Photo, RecyclerView.ViewHolder>(NoteDiffUtil()) {
//
//    lateinit var listener: PhotoListener
//
//    private val VIEW_TYPE_ITEM = 1
//    private val VIEW_TYPE_LOADING = 0
//
//
//    inner class PhotoViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){
//
//        fun bind(data: Photo){
//            binding.photo = data
//            binding.item.setOnClickListener {
//                listener.onClick(data)
//            }
//
//        }
//    }
//
//    inner class LoadViewHolder(private val binding: ItemLoadStateBinding): RecyclerView.ViewHolder(binding.root){
//        fun bind(){
//
//
//        }
//    }
//
//
//
////    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
////        val note = getItem(position)!!
////        holder.bind(note)
////    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = getItem(position)!!
//        when(holder){
//            is PhotoViewHolder ->{ holder.bind(data)}
//            is LoadViewHolder ->{holder.bind()}
//        }
//    }
//
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return when(viewType){
//            VIEW_TYPE_ITEM ->{
//                val binding = ItemPhotoBinding.inflate(inflater,parent,false)
//                PhotoViewHolder(binding)
//            }
//            VIEW_TYPE_LOADING ->{
//                val binding = ItemLoadStateBinding.inflate(inflater,parent,false)
//                LoadViewHolder(binding)
//            }
//            else -> { throw java.lang.Exception("Error")}
//        }
//
//
//    }
//
//
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.PhotoViewHolder {
////        val inflater = LayoutInflater.from(parent.context)
////        val binding = ItemPhotoBinding.inflate(inflater,parent,false)
////        return PhotoViewHolder(binding)
////    }
//
//
//    class NoteDiffUtil : DiffUtil.ItemCallback<Photo>() {
//        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id == newItem.id
//        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem
//    }
//
//
//
//    interface PhotoListener {
//        fun onClick(photo: Photo)
//    }
//
//
//
//}

