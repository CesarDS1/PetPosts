package com.cesar.petpost.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cesar.petpost.R
import com.cesar.petpost.data.dataSource.PostDataSource
import com.cesar.petpost.mvp.presenter.MainPresenter
import com.cesar.petpost.mvp.view.MainView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainPresenter: MainPresenter

    lateinit var petPostsRecyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var sortSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        supportActionBar?.setDisplayShowTitleEnabled(true)

        initViews()

        title_screen.run { setText(R.string.app_name) }

        floatingActionButton.setOnClickListener {
            startActivityForResult(Intent(this, CreatePostActivity::class.java), 0)
        }

        mainPresenter = MainPresenter(MainView(this), PostDataSource())
        mainPresenter.initViews()

        mainPresenter.getPetPosts()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                mainPresenter.getPetPosts()
            }

        }
    }

    private fun initViews() {
        petPostsRecyclerView = recycler_view_pet_post
        floatingActionButton = add_post
        sortSpinner = spinner_sort
    }


}
