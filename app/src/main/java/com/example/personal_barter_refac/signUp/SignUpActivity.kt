package com.example.personal_barter_refac.signUp

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.personal_barter_refac.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()
    private val editTexts get() = with(binding){
        listOf(
            etName,
            etId,
            etPw
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        initView()
        checkValid()
    }

    private fun initView(){
        editTexts.forEach {editText ->
            editText.addTextChangedListener {
                editText.checkValidElements()
            }

            editText.setOnFocusChangeListener { _, hasFocus ->
                if(!hasFocus) editText.checkValidElements()
            }
        }
    }

    private fun checkValid(){
        viewModel.errorUiState.observe(this@SignUpActivity){uiState ->
            with(binding){
                tvNameWarning.text = when(uiState.name){
                    SignUpValidUiState.Name -> "이름을 입력해주세요."
                    else -> ""
                }

                tvIdWarning.text = when(uiState.emailId){
                    SignUpValidUiState.EmailBlank -> "아이디를 입력해 주세요"
                    else ->""
                }

                tvPwWarning.text = when(uiState.passwordInput){
                    SignUpValidUiState.PasswordInputLength -> "10자리 이상 입력해 주세요"
                    SignUpValidUiState.PasswordInputUpperCase -> "대문자를 포함시켜 주세요."
                    else -> ""
                }
            }
        }
    }

    private fun EditText.checkValidElements() = with(binding){
        when(this@checkValidElements){
            etName -> viewModel.checkValidName(etName.text.toString())
            etId -> viewModel.checkValidEmail(etId.text.toString())
            etPw -> viewModel.checkValidPasswordInput(etPw.text.toString())
        }
    }
}