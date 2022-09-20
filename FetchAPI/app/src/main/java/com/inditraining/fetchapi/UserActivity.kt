package com.inditraining.fetchapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.inditraining.fetchapi.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {
    private lateinit var activityUserBinding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(activityUserBinding.root)

        val dataUser = intent.getParcelableExtra<DataItem>(ListUserActivity.RV_ITEM_ID)
        if (dataUser!!.firstName.isNotEmpty())
            activityUserBinding.apply {
                tvUserDetailFirstname.text = dataUser.firstName
                tvUserDetailLastname.text = dataUser.lastName
                userDetailEmailTv.text = dataUser.email
                Glide.with(activityUserBinding.root)
                    .load(dataUser.avatar)
                    .into(ivUserPhotoDetail)
            }
    }
}