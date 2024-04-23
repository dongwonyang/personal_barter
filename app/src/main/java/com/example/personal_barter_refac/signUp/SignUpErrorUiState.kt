package com.example.personal_barter_refac.signUp

import java.util.Objects

data class SignUpErrorUiState(
    val name: SignUpValidUiState,
    val emailId: SignUpValidUiState,
    val passwordInput: SignUpValidUiState,
    val enable: Boolean
) {
    companion object {
        fun init() = SignUpErrorUiState(
            name = SignUpValidUiState.Init,
            emailId = SignUpValidUiState.Init,
            passwordInput = SignUpValidUiState.Init,
            enable = false
        )
    }
}

sealed interface SignUpValidUiState {
    data object Init : SignUpValidUiState
    data object Valid : SignUpValidUiState

    data object Name : SignUpValidUiState

    data object EmailBlank : SignUpValidUiState

    data object PasswordInputLength : SignUpValidUiState
    data object PasswordInputUpperCase : SignUpValidUiState
}
