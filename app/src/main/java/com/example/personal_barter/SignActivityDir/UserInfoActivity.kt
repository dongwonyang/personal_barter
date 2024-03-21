package com.example.personal_barter.SignActivityDir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.personal_barter.MainActivityDir.UserInfo
import com.example.personal_barter.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_userInfo)
        setSupportActionBar(toolbar)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager_userInfo)
        viewPager.adapter = UserInfoViewPagerAdapte(this)
        viewPager.isUserInputEnabled = true

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout_userInfo)
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = "page${position + 1}"
        }.attach()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        menuInflater.inflate(R.menu.bottom_navigate_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.toolbar_search ->{
                if(!UserInfo.isSingIn()){
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}