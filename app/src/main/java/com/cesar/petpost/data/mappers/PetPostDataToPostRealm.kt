package com.cesar.petpost.data.mappers

import com.cesar.petpost.data.realm.AuthorRealm
import com.cesar.petpost.data.realm.MessageRealm
import com.cesar.petpost.data.realm.PostRealm
import com.cesar.petpost.data.service.PostData

object PetPostDataToPostRealm {

    fun transform(postData: PostData): PostRealm {
        val message = postData.messageData
        val author = postData.authorData
        message.body.image
        val messageRealm = MessageRealm(
            message.likes,
            message.views,
            message.date,
            message.id,
            message.body.image,
            message.body.text
        )
        val authorRealm = AuthorRealm(
            author.id,
            author.username,
            author.name,
            author.lastName,
            author.email,
            author.avatar
        )
        return PostRealm(authorRealm, messageRealm)
    }
}