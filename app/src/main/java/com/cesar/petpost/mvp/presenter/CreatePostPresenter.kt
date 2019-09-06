package com.cesar.petpost.mvp.presenter

import com.cesar.petpost.data.dataSource.PostDataSource
import com.cesar.petpost.mvp.view.CreatePostView

class CreatePostPresenter(
    private val createPostView: CreatePostView,
    private val postDataSource: PostDataSource
) {
    fun savePost(description: String) {
        postDataSource.savePost(description)
        createPostView.goBackActivity()
    }
}