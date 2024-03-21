package com.example.personal_barter.SignActivityDir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.personal_barter.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val buttonSingUp = findViewById<Button>(R.id.button_signUp_signUp)
        val editTextName = findViewById<EditText>(R.id.editText_signUp_name)
        val editTextId = findViewById<EditText>(R.id.editText_signUp_ID)
        val editTextPw = findViewById<EditText>(R.id.editText_signUp_PW)

        buttonSingUp.setOnClickListener {
            val textName = editTextName.text.toString()
            val textId = editTextId.text.toString()
            val textPw = editTextPw.text.toString()

            if(textName.isEmpty() || textId.isEmpty() || textPw.isEmpty()){
                Toast.makeText(this, "정보를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            } else{
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("ID", textId)
                intent.putExtra("PW", textPw)
                startActivity(intent)
            }

        }
    }
}