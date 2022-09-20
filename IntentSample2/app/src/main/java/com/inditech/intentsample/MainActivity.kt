package com.inditech.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var intentActivity: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.edEmail)
        edtPassword = findViewById(R.id.edPassword)
        btnLogin = findViewById(R.id.btnLogin)

        intentActivity = Intent(this@MainActivity, HomePage::class.java)

        btnLogin.setOnClickListener {

            val loginObject = Login(
                edtEmail.text.toString(),
                edtPassword.text.toString(),
                true
            )

            intentActivity.putExtra(HomePage.EXTRA_LOGIN, loginObject)
            startActivity(intentActivity)

        }

    }
}