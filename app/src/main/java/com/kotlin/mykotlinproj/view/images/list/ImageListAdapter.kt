package com.kotlin.mykotlinproj.view.images.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.databinding.RowImageListItemBinding

class ImageListAdapter(val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ImageViewHolder>() {

    private var items: MutableList<UnsplashPhoto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val dataBinding =
            RowImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(dataBinding.root, itemClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setImageList(data: List<UnsplashPhoto>) {
        if (!data.isNullOrEmpty()) {
            val size = items.size
            this.items.addAll(data)
            notifyItemRangeInserted(size, data.size)
        }
    }

    fun clear() {
        items.clear()
    }

    interface OnItemClickListener {
        fun onItemClick(item: UnsplashPhoto?)
    }
}