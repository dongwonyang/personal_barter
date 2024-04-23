package com.example.personal_barter_refac.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val _errorUiState: MutableLiveData<SignUpErrorUiState> =
        MutableLiveData(SignUpErrorUiState.init())
    val errorUiState: LiveData<SignUpErrorUiState> = _errorUiState

    fun checkValidName(text: String) {
        _errorUiState.value = errorUiState.value?.copy(
            name = if (text.isBlank()) {
                SignUpValidUiState.Name
            } else {
                SignUpValidUiState.Valid
            }
        )
    }

    fun checkValidEmail(text: String) {
        _errorUiState.value = errorUiState.value?.copy(
            emailId = when {
                text.isEmpty() -> SignUpValidUiState.EmailBlank
                else -> SignUpValidUiState.Valid
            }
        )
    }

    fun checkValidPasswordInput(text: String) {
        _errorUiState.value = errorUiState.value?.copy(
            passwordInput = when {
                text.length < 10 -> SignUpValidUiState.PasswordInputLength
                !text.includeUpperCase() -> SignUpValidUiState.PasswordInputUpperCase
                else -> SignUpValidUiState.Valid
            }
        )
    }



    private fun String.includeUpperCase() = Regex("[A-Z]").containsMatchIn(this)
}

