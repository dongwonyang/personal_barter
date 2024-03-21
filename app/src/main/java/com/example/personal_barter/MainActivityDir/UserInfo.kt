package com.example.personal_barter.MainActivityDir

object UserInfo {
    private var id: String? = null
    private var pw: String? = null

    fun singIn(id: String, pw: String) {
        this.id = id
        this.pw = pw
    }

    fun getPw(): String {
        return pw ?: ""
    }

    fun getId(): String {
        return id ?: ""
    }

    fun setPw(pw: String) {
        this.pw = pw
    }

    fun isSingIn(): Boolean {
        return !(id.isNullOrEmpty() || pw.isNullOrEmpty())
    }
}