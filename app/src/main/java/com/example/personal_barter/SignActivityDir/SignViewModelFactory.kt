package com.example.personal_barter.SignActivityDir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignViewModel::class.java))
            return SignViewModel() as T
        throw throw IllegalArgumentException("Unknown ViewModel class")
    }
}