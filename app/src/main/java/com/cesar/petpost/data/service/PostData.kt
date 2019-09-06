package com.cesar.petpost.data.service

import com.google.gson.annotations.SerializedName

data class PostData(@SerializedName("author")var authorData: AuthorData,@SerializedName("message") var messageData: MessageData)