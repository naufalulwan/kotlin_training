package com.inditraining.fetchapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inditraining.fetchapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnGetListUsers.setOnClickListener {
            val intentToListUsersActivity = Intent(this@MainActivity,ListUserActivity::class.java)
            startActivity(intentToListUsersActivity)
        }

        activityMainBinding.btnGetUsers.setOnClickListener {
            val intentToUserActivity = Intent(this@MainActivity,SearchUserActivity::class.java)
            startActivity(intentToUserActivity)
        }

        activityMainBinding.btnUploadUsers.setOnClickListener {
            val intentToUploadUserActivity = Intent(this@MainActivity,UploadUserActivity::class.java)
            startActivity(intentToUploadUserActivity)
        }
    }
}