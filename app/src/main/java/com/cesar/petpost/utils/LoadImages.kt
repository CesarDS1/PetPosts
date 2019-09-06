package com.cesar.petpost.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesar.petpost.mvp.model.PetPost

class LoadImages {

    fun loadWithGlide(context: Context, post: PetPost, imageView: ImageView) {
        val randomParameter = (1..10).random()
        Glide.with(context)
            .load(post.image + "/" + randomParameter.toString())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageView)
    }

}