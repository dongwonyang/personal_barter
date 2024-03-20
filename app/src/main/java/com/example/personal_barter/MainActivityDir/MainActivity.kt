package com.example.personal_barter.MainActivityDir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.personal_barter.R
import com.example.personal_barter.SignActivityDir.SignInActivity
import com.example.personal_barter.SignActivityDir.SignViewModel
import com.example.personal_barter.SignActivityDir.SignViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.title = "Tool Bar"

        val viewPager = findViewById<ViewPager2>(R.id.viewPager_event_banner)
        viewPager.adapter = MainViewPagerAdapter(this)
        viewPager.isUserInputEnabled = true

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_main_rank)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainRecyclerViewAdapter(getMainRankData())

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = "Page ${position + 1}"
        }.attach()

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    Log.d("timer check", "dd")
                    if (viewPager.currentItem + 1 < viewPager.adapter?.itemCount ?: 0)
                        viewPager.currentItem += 1
                    else viewPager.currentItem = 0
                }
            }
        }, 3000, 3000) // 3초마다 페이지 변경, 시작 전 3초 중간 시간 3초


    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
        timer = null
    }

    override fun onRestart() {
        super.onRestart()
        if (timer == null) {
            timer = Timer()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        menuInflater.inflate(R.menu.bottom_navigate_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.toolbar_search -> {
                return true
            }
            R.id.toolbar_info -> {
                if(!UserInfo.isSingIn()) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
            R.id.navigation_home -> {
            }
            R.id.navigation_purchase -> {
            }
            R.id.navigation_sale -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getMainRankData(): List<MainRankData> {
        return listOf<MainRankData>(
            MainRankData(1, "아이유 콘서트 티켓", 24),
            MainRankData(2, "샤넬백", 23),
            MainRankData(3, "장난감세트", 20),
            MainRankData(4, "쌀포대", 14),
            MainRankData(5, "문화상품권 5만원", 10),
            MainRankData(6, "20만원", 10),
            MainRankData(7, "마우스", 9),
            MainRankData(8, "모니터", 5),
            MainRankData(9, "라면1봉지", 4)
        )
    }
}