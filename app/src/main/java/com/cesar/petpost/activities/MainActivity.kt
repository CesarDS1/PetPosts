package com.cesar.petpost.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        petPostsRecyclerView = recycler_view_pet_post
        floatingActionButton = add_post
        sortSpinner = spinner_sort

        title_screen.apply {
            setText(R.string.app_name)
        }


        val mainView = MainView(this)
        val petPostDataSource = PostDataSource()
        mainPresenter = MainPresenter(mainView, petPostDataSource)
        mainPresenter.getPetPosts()

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivityForResult(intent, 0)
        }
        setupSpinner()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                mainPresenter.getPetPosts()
            }

        }
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.sort_types,
            android.R.layout.simple_spinner_dropdown_item
        )
        sortSpinner.adapter = spinnerAdapter
    }
}
