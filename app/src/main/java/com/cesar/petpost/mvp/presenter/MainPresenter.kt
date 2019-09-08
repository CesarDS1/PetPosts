package com.cesar.petpost.mvp.presenter

import com.cesar.petpost.data.ReadJson
import com.cesar.petpost.data.dataSource.PostDataSource
import com.cesar.petpost.mvp.model.PetPost
import com.cesar.petpost.mvp.view.MainView

class MainPresenter(private val mainView: MainView, private val petPostDataSource: PostDataSource) {

    fun initViews(){
        mainView.setupSpinner()
    }

    fun getPetPosts() {
        val countPosts = petPostDataSource.countPosts()

        if (countPosts == 0L) {
            ReadJson.getJsonDataString(mainView.mainActivity)?.let {
                petPostDataSource.savePostFromAssets(it)
                mainView.showListPost(petPostDataSource.getAllPetPosts() as ArrayList<PetPost>)
            }
        } else {
            mainView.showListPost(petPostDataSource.getAllPetPosts() as ArrayList<PetPost>)
        }
    }
}