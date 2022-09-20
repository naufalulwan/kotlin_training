package com.inditraining.latihan_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.TEXT_ALIGNMENT_INHERIT
import androidx.appcompat.app.ActionBar
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()

        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if(fragment !is HomeFragment) {
            Log.d("FlexibleFragment", "Fragment Name : " + HomeFragment::class.java.simpleName)

            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mHomeFragment, HomeFragment::class.java.simpleName)
                .commit()
        }

        supportActionBar?.title = "Main Activity"

    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume: Ini adalah lifecycle onResume ketika djalankan")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy: Ini adalah lifecycle onDestroy ketika djalankan")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause: Ini adalah lifecycle onPause ketika djalankan")
    }
}