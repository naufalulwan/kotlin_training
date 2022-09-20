package com.inditraining.latihan_training

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvProfile: TextView
    private lateinit var btnShare: Button
    private lateinit var btnGallery: Button
    private lateinit var ivGallery: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.title = "Profile Activity"

        tvProfile = findViewById(R.id.tv_profile)

        ivGallery = findViewById(R.id.iv_gallery)

        tvProfile.text = intent.getStringExtra(EXTRA_APP)

        btnShare = findViewById(R.id.btn_share)
        btnGallery = findViewById(R.id.btn_gallery)

        btnShare.setOnClickListener(this)
        btnGallery.setOnClickListener(this)

    }

    companion object{
        var EXTRA_APP = "extra_app"
    }

    @SuppressLint("IntentReset")
    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_share) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, tvProfile.text)
            startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
        } else if (v?.id == R.id.btn_gallery) {
            if(ActivityCompat.checkSelfPermission(this@ProfileActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this@ProfileActivity,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                   1
                )
            } else {
                val pickIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                pickIntent.type = "image/*"

                result.launch(Intent.createChooser(pickIntent, "Select Picture"))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    val pickIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    pickIntent.type = "image/*"
                    result.launch(Intent.createChooser(pickIntent, "Select Picture"))
                } else {
                    // dont allow message or action
                }}
            else -> {}
        }
    }

    private var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            ivGallery.setImageURI(data?.data)
            ivGallery.scaleType = ImageView.ScaleType.FIT_CENTER
            tvProfile.text = result.data.toString()

        }
    }


}