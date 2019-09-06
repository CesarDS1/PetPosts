package com.cesar.petpost.mvp.model

data class PetPost(
    var content: String,
    var image: String,
    var authorUsername: String,
    var date: String,
    var likes: Int,
    var views: Int
)