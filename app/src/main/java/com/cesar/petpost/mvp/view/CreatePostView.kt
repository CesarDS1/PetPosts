package com.cesar.petpost.mvp.view

import com.cesar.petpost.activities.CreatePostActivity
import android.app.Activity
import android.content.Intent



class CreatePostView(private val createPostActivity: CreatePostActivity) {

    fun goBackActivity() {
        val returnIntent = Intent()
        createPostActivity.setResult(Activity.RESULT_OK, returnIntent)
        createPostActivity.finish()
    }
}