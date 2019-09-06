package com.cesar.petpost.data.realm

import io.realm.RealmObject

open class MessageRealm(
    var likes: Int = 0,
    var view: Int = 0,
    var date: String = "",
    var id: String = "0",
    var image: String = "",
    var text: String = ""
) : RealmObject()
