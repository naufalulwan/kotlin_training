package com.inditraining.fetchapi

import com.google.gson.annotations.SerializedName

data class UploadUserResponse(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("job")
    val job: String
)