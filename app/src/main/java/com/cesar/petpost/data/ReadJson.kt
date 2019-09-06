package com.cesar.petpost.data

import android.content.Context
import com.cesar.petpost.data.service.PetPostsData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

object ReadJson {

    fun getJsonDataString(context: Context): PetPostsData? {
        var content: String? = null
        BufferedReader(InputStreamReader(context.assets.open("pet_posts_data.json"))).use {
            var line = it.readLine()
            while (line != null) {
                content = line
                line = it.readLine()
            }
        }
        val type = genericType<PetPostsData>()

        return Gson().fromJson<PetPostsData>(content, type)
    }

    private inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type
}