package com.cesar.petpost.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesar.petpost.R
import com.cesar.petpost.data.dataSource.PostDataSource
import com.cesar.petpost.mvp.presenter.CreatePostPresenter
import com.cesar.petpost.mvp.view.CreatePostView
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    lateinit var createPostPresenter: CreatePostPresenter

    lateinit var titleEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var saveButton: Button
    lateinit var postImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create_post)

        initViews()
        setSupportActionBar(findViewById(R.id.my_toolbar))
        title_screen.apply { text = context.getString(R.string.create_post) }

        createPostPresenter = CreatePostPresenter(CreatePostView(this), PostDataSource())

        saveButton.setOnClickListener {
            createPostPresenter.savePost(descriptionEditText.text.toString())
        }
        val randomParameter = (1..10).random()
        Glide.with(this)
            .load("$IMAGE_URL_DEFAULT/$randomParameter")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(postImageView)
    }

    private fun initViews() {
        titleEditText = editText_title
        descriptionEditText = editText_description
        saveButton = button_save
        postImageView = imageView_post
    }

    companion object {
        const val IMAGE_URL_DEFAULT = "http://lorempixel.com/640/480/cats"
    }
}
