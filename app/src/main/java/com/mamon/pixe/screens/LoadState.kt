package com.mamon.pixe.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mamon.pixe.R
import com.mamon.pixe.databinding.ItemLoadStateBinding

class LoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_load_state, parent, false)
) {
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
    private val errorMsg: TextView = itemView.findViewById(R.id.error_msg)
    private val retry: Button = itemView.findViewById<Button>(R.id.retry_button)
        .also { it.setOnClickListener { retry.invoke() } }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }
        progressBar.visibility = toVisibility(loadState is LoadState.Loading)
        retry.visibility = toVisibility(loadState !is LoadState.Loading)
        errorMsg.visibility = toVisibility(loadState !is LoadState.Loading)
    }

    private fun toVisibility(constraint: Boolean): Int = if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * Adapter which displays a loading spinner when `state = LoadState.Loading`, and an error
 * message and retry button when `state is LoadState.Error`.
 */
class MyLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(parent, retry)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)
}

//class CatsLoadStateAdapter(
//    private val retry: () -> Unit
//) : LoadStateAdapter<CatsLoadStateViewHolder>() {
//    override fun onBindViewHolder(holder: CatsLoadStateViewHolder, loadState: LoadState) {
//        holder.bind(loadState)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        loadState: LoadState
//    ): CatsLoadStateViewHolder {
//        return CatsLoadStateViewHolder.create(parent, retry)
//    }
//}
//
//
//
//class CatsLoadStateViewHolder(
//    private val binding: ItemLoadStateBinding,
//    retry: () -> Unit
//) : RecyclerView.ViewHolder(binding.root) {
//
//    init {
//        binding.retryButton.setOnClickListener { retry.invoke() }
//    }
//
//    fun bind(loadState: LoadState) {
//        if (loadState is LoadState.Error) {
//            binding.errorMsg.text = loadState.error.localizedMessage
//        }
//        binding.progressBar.isVisible = loadState is LoadState.Loading
//        binding.retryButton.isVisible = loadState !is LoadState.Loading
//        binding.errorMsg.isVisible = loadState !is LoadState.Loading
//    }
//
//    companion object {
//        fun create(parent: ViewGroup, retry: () -> Unit): CatsLoadStateViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_load_state, parent, false)
//            val binding = ItemLoadStateBinding.bind(view)
//            return CatsLoadStateViewHolder(binding, retry)
//        }
//    }
//}