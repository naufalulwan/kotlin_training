package com.inditraining.fetchapi


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.inditraining.fetchapi.databinding.ActivityListUserBinding
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUserActivity : AppCompatActivity() {

    private lateinit var listUserActivityBinding: ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listUserActivityBinding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(listUserActivityBinding.root)

        listUserActivityBinding.rvListUsers.setHasFixedSize(true)

        searchList()
    }

    private fun searchList(){
        val client = ApiConfig.getApiService().getUserList("1")
        client.enqueue(object : Callback<ListUserResponse> {
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                        showRecyclerView(responseBody.data)
                        Log.i(TAG, "onSuccess : ${response.headers()}, Response Status : ${response.code()}")
                    }
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}, Response Status : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message} ${t.localizedMessage}")
            }
        })
    }

    private fun showRecyclerView(data : List<DataItem>){
        val rvAdapter = ListUserAdapter(data)
        listUserActivityBinding.rvListUsers.layoutManager = GridLayoutManager(this,2)
        listUserActivityBinding.rvListUsers.adapter = rvAdapter

        rvAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(userData: DataItem) {
                val intentToDetail = Intent(this@ListUserActivity,UserActivity::class.java)
                intentToDetail.putExtra(RV_ITEM_ID,userData)
                startActivity(intentToDetail)
            }
        })
    }

    companion object {
        const val RV_ITEM_ID = "RV_ITEM_ID"
    }
}