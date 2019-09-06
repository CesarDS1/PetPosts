package com.cesar.petpost.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
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
        titleEditText = editText_title
        descriptionEditText = editText_description
        saveButton = button_save
        postImageView = imageView_post

        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        title_screen.apply { text = context.getString(R.string.create_post) }
        val createPostView = CreatePostView(this)
        val postDataSource = PostDataSource()
        createPostPresenter = CreatePostPresenter(createPostView, postDataSource)

        saveButton.setOnClickListener {
            val description = descriptionEditText.text.toString()
            createPostPresenter.savePost(description)

        }
        Glide.with(this)
            .load("http://lorempixel.com/640/480/cats")
            .centerCrop()
            .into(postImageView)
    }
}
