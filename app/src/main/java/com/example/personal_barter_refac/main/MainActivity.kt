package com.example.personal_barter_refac.main

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.personal_barter_refac.R
import com.example.personal_barter_refac.databinding.ActivityMainBinding
import com.example.personal_barter_refac.main.fragment.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        botNaviFunc()
        setSupportActionBar(binding.tbTop)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setFragment(HomeFragment())
    }


    private fun botNaviFunc(){
        binding.botNavi.setOnNavigationItemReselectedListener { item ->
            when(item?.itemId){
                R.id.botNavi_home ->{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.botNavi_purchase-> {}
                R.id.botNavi_sale -> {}
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_top, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.toolbar_search ->{}
            R.id.toolbar_info -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(fragment: Fragment){
        this@MainActivity.supportFragmentManager.beginTransaction()
            .replace(binding.fl.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}