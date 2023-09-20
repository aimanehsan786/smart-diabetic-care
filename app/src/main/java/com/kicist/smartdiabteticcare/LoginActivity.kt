package com.kicist.smartdiabteticcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kicist.smartdiabteticcare.databinding.ActivityLoginBinding
import com.kicist.smartdiabteticcare.helpers.LinkHelper

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LinkHelper.makeTextClickable(binding.forgotPassword, fun () {
            startActivity(Intent(applicationContext, ForgotPasswordActivity::class.java))
        }, false)

        binding.backArrow.setOnClickListener {
            startActivity(Intent(applicationContext, StartupActivity::class.java))
        }
    }
}