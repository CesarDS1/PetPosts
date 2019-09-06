package com.cesar.petpost.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cesar.petpost.R
import com.cesar.petpost.mvp.model.PetPost
import com.cesar.petpost.utils.LoadImages

class PetPostAdapter(val petPostList: ArrayList<PetPost?>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var loadImages: LoadImages = LoadImages()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.item_petpost -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_petpost, parent, false)
                return PetPostViewHolder(view)
            }
            R.layout.item_loading -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false)
                return LoadingViewHolder(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false)
                return LoadingViewHolder(view)
            }
        }

    }

    override fun getItemCount() = petPostList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PetPostViewHolder) {
            val post = petPostList[position]
            if (post != null) {
                holder.userNameTv.text = post.authorUsername
                holder.datePostTv.text = post.date
                holder.likesTv.text = post.likes.toString()
                holder.viewsTv.text = post.views.toString()
                loadImages.loadWithGlide(context, post, holder.imagePostIv)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (petPostList[position] == null) {
            R.layout.item_loading
        } else {
            R.layout.item_petpost
        }

    }

    class PetPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagePostIv: ImageView = itemView.findViewById(R.id.imageView_image_post)
        val userNameTv: TextView = itemView.findViewById(R.id.textView_username)
        val datePostTv: TextView = itemView.findViewById(R.id.textView_date)
        var likesTv: TextView = itemView.findViewById(R.id.textView_likes)
        var viewsTv: TextView = itemView.findViewById(R.id.textView_view)
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar_loading)
    }

}