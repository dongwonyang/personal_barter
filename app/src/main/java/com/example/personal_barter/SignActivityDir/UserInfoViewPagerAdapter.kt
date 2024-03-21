package com.example.personal_barter.SignActivityDir

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class UserInfoViewPagerAdapte(fm: FragmentActivity): FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return UserInfoFrag.newInstance(position)
    }

}