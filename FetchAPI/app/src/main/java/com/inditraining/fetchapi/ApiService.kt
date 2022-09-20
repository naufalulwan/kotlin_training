package com.inditraining.fetchapi

import SearchUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUserList(
        @Query("page") page: String
    ) : Call<ListUserResponse>

    @GET("users/{userid}")
    fun getUserById(
        @Path("userid") userId:String
    ) : Call<SearchUserResponse>

    @FormUrlEncoded
    @POST("users")
    fun uploadUser(
        @Field("name") name:String,
        @Field("job") job:String
    ) : Call<UploadUserResponse>

}