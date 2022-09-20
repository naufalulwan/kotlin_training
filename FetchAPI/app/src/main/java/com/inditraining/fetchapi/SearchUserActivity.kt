package com.inditraining.fetchapi

import SearchUserResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inditraining.fetchapi.databinding.ActivitySearchUserBinding

import android.widget.SearchView

import android.content.ContentValues

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class SearchUserActivity : AppCompatActivity() {

    private lateinit var searchUserBinding: ActivitySearchUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchUserBinding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(searchUserBinding.root)

        searchUserBinding.searchUserInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchUserById(query!!)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchUserById(query :String){
        searchUserBinding.cardView.visibility = View.GONE
        searchUserBinding.searchUserLoading.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getUserById(query)
        client.enqueue(object : Callback<SearchUserResponse>{
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                        searchUserBinding.tvUserName.text = StringBuilder(responseBody.data.firstName).append(" ").append(responseBody.data.lastName)
                        searchUserBinding.tvUserEmail.text = responseBody.data.email
                        Glide.with(searchUserBinding.root)
                            .load(responseBody.data.avatar)
                            .into(searchUserBinding.imgUserPhoto)
                    }
                    searchUserBinding.searchUserLoading.visibility = View.GONE
                    searchUserBinding.cardView.visibility = View.VISIBLE
                }else{
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
                    searchUserBinding.searchUserLoading.visibility = View.GONE
                    searchUserBinding.cardView.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                searchUserBinding.searchUserLoading.visibility = View.GONE
                searchUserBinding.cardView.visibility = View.VISIBLE
            }
        })
    }
}