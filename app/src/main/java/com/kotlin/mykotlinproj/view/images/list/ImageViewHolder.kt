package com.kotlin.mykotlinproj.view.images.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mykotlinproj.R
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.squareup.picasso.Picasso

class ImageViewHolder(root: View, val itemClickListener: ImageListAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(root) {

    val imageView:ImageView = root.findViewById(R.id.item_photo)
    val name:TextView = root.findViewById(R.id.item_name)

    fun bind(unsplashPhoto: UnsplashPhoto) {
        Picasso.get().load(unsplashPhoto.urls.small)
//            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(imageView)
        imageView.setOnClickListener {
            itemClickListener.onItemClick(unsplashPhoto)
        }
    }
}