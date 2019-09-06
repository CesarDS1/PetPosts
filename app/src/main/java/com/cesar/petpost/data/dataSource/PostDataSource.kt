package com.cesar.petpost.data.dataSource

import com.cesar.petpost.data.mappers.PetPostDataToPostRealm
import com.cesar.petpost.data.mappers.PetPostRealmToPetPost
import com.cesar.petpost.data.realm.AuthorRealm
import com.cesar.petpost.data.realm.MessageRealm
import com.cesar.petpost.data.realm.PostRealm
import com.cesar.petpost.data.service.PetPostsData
import com.cesar.petpost.mvp.model.PetPost
import io.realm.Realm
import java.io.FileDescriptor
import java.util.*

class PostDataSource {

    fun savePostFromAssets(listPosts: PetPostsData) {
        val realm = Realm.getDefaultInstance()
        realm.use { realmInstance ->
            realmInstance.executeTransaction {
                listPosts.posts.forEach {
                    realmInstance.copyToRealm(PetPostDataToPostRealm.transform(it))
                }
            }
        }
    }

    fun savePost(description: String) {
        val authorRealm = AuthorRealm()
        val messageRealm = MessageRealm(
            (0..1000).random(),
            (0..100).random(),
            Date().toString(),
            (0..1000).random().toString() + ('a'..'z').random(),
            "http://lorempixel.com/640/480/cats", description
        )
        val postRealm = PostRealm(authorRealm, messageRealm)
        val realm = Realm.getDefaultInstance()

        realm.use { realmInstance ->
            realmInstance.executeTransaction {
                realmInstance.copyToRealm(postRealm)
            }
        }
    }

    fun countPosts(): Long {
        val realm = Realm.getDefaultInstance()
        realm.use { realmInstance ->
            val postRealmResult = realmInstance.where(PostRealm::class.java)
            return postRealmResult.count()
        }
    }

    fun getAllPetPosts(): List<PetPost> {
        val realm = Realm.getDefaultInstance()
        realm.use { realmInstance ->
            val postRealmResult = realmInstance.where(PostRealm::class.java).findAll()
            return postRealmResult.map<PostRealm, PetPost> {
                PetPostRealmToPetPost.transform(it)
            }

        }
    }
}