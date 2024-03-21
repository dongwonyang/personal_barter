package com.example.personal_barter.SignActivityDir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personal_barter.MainActivityDir.UserInfo
import com.example.personal_barter.R

class UserInfoFrag: Fragment() {
    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_search, container, false)
        return view
    }

    companion object{
        const val ARG_TYPE  = "UserInfo_Page"
        fun newInstance(position: Int): Fragment{
            val frag = UserInfoFrag()
            val args = Bundle()

            args.putInt("UserInfo_Page", position)
            frag.arguments = args

            return frag
        }
    }
}