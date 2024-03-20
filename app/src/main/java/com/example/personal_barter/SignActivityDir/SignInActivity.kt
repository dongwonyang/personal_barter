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
import com.example.personal_barter.R

class SignInActivity : AppCompatActivity() {
    private lateinit var signViewModel: SignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val editTextID = findViewById<EditText>(R.id.editText_signIn_ID)
        val editTextPW = findViewById<EditText>(R.id.editText_signIn_PW)
        val buttonSignIn = findViewById<Button>(R.id.button_signIn)
        val buttonSignUp = findViewById<Button>(R.id.button_signUp)

        signViewModel = ViewModelProvider(this, SignViewModelFactory()).get(SignViewModel::class.java)

        buttonSignIn.setOnClickListener {
            val ID = editTextID.text.toString()
            val PW = editTextPW.text.toString()
            if(ID.isEmpty() || PW.isEmpty()){
                Toast.makeText(this, "회원 정보를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            } else{
                signViewModel.putUserInfo(ID, PW)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        buttonSignUp.setOnClickListener {
        }
    }
}