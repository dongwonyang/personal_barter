package com.example.personal_barter_refac.signIn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personal_barter_refac.databinding.ActivitySignInBinding
import com.example.personal_barter_refac.main.MainActivity

class SignInActivity : AppCompatActivity() {
    private val binding: ActivitySignInBinding by lazy { ActivitySignInBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

    private fun setBtnClick(){
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}