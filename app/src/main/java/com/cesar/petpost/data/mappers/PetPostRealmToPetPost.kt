package com.cesar.petpost.data.mappers

import com.cesar.petpost.data.realm.PostRealm
import com.cesar.petpost.mvp.model.PetPost

object PetPostRealmToPetPost {

    fun transform(postRealm: PostRealm): PetPost {

        val message = postRealm.message
        val author = postRealm.author
        return if (message != null && author != null) PetPost(
            message.text,
            message.image,
            author.userName,
            message.date,
            message.likes,
            message.view
        )
        else PetPost("", "", "", "", 0,0)

    }
}