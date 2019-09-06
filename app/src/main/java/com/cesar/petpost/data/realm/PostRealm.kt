package com.cesar.petpost.data.realm

import io.realm.RealmObject

open class PostRealm(
    var author: AuthorRealm? = null,
    var message: MessageRealm? = null
) : RealmObject()