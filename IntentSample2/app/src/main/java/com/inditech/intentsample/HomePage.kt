package com.inditech.intentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomePage : AppCompatActivity() {

    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }

    private lateinit var tvResultEmail : TextView
    private lateinit var tvResultPassword : TextView
    private lateinit var tvResultStatus : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        tvResultEmail = findViewById(R.id.tv_ResultEmail)
        tvResultPassword = findViewById(R.id.tv_ResultPassword)
        tvResultStatus = findViewById(R.id.tv_ResultStatus)
        val login = intent.getParcelableExtra<Login>(EXTRA_LOGIN) as Login

        tvResultEmail.text = "Email : ${login.email}"
        tvResultPassword.text = "Password : ${login.password}"
        tvResultStatus.text = "Status Login : ${login.statusLogin}"
    }
}