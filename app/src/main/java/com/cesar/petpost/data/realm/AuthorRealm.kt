package com.cesar.petpost.data.realm

import io.realm.RealmObject

open class AuthorRealm(
    var id: String= "158741",
    var userName: String = "cesarNr",
    var name: String= "Cesar",
    var lastName: String= "Nieto",
    var email: String= "sergio.cesar.12@gmail.com",
    var avatar: String = "https://s3.amazonaws.com/uifaces/faces/twitter/jmillspaysbills/128.jpg"
): RealmObject()