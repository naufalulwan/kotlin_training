package com.inditech.implicitintentsample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.inditech.implicitintentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnStart = binding.btnStart
        val tvStatus = binding.tvStatus

        btnStart.setOnClickListener {
            try {
                for (i in 0..10) {
                    Thread.sleep(500)
                    val percentage = i * 10
                    if (percentage == 100) {
                        tvStatus.setText(R.string.task_completed)
                    } else {
                        tvStatus.text = String.format(getString(R.string.compressing), percentage)
                    }
                }
            } catch (e: InterruptedException){
            }
        }
    }
}