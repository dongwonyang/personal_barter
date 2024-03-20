package com.example.personal_barter.SignActivityDir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.personal_barter.MainActivityDir.MainActivity
import com.example.personal_barter.MainActivityDir.UserInfo
import com.example.personal_barter.R

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val editTextID = findViewById<EditText>(R.id.editText_signIn_ID)
        val editTextPW = findViewById<EditText>(R.id.editText_signIn_PW)
        val buttonSignIn = findViewById<Button>(R.id.button_signIn)
        val buttonSignUp = findViewById<Button>(R.id.button_signUp)


        buttonSignIn.setOnClickListener {
            val id = editTextID.text.toString()
            val pw = editTextPW.text.toString()
            if(id.isEmpty() || pw.isEmpty()){
                Toast.makeText(this, "회원 정보를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            } else{
                val intent = Intent(this, MainActivity::class.java)
                UserInfo.singIn(id, pw)
                startActivity(intent)
            }
        }

        buttonSignUp.setOnClickListener {
        }
    }
}