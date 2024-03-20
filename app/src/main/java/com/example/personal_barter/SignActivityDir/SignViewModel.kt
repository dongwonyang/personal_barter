package com.example.personal_barter.SignActivityDir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignViewModel : ViewModel() {
    private val ID = MutableLiveData<String>()
    private val PW = MutableLiveData<String>()

    fun putUserInfo(id: String, pw: String){
        ID.value = id
        PW.value = pw
    }

    fun getUserInfo(): Pair<String, String>{
        return Pair(ID.value ?: "", PW.value ?: "")
    }
    fun isSingIn(): Boolean{
        return !(ID.value.isNullOrEmpty() || PW.value.isNullOrEmpty())
    }

    fun getData(): MutableLiveData<String> = ID
}