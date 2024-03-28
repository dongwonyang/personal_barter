package com.example.personal_barter.MainActivityDir

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.personal_barter.PurchaseDir.PurchaseActivity
import com.example.personal_barter.R
import com.example.personal_barter.SaleDir.SaleActivity
import com.example.personal_barter.SignActivityDir.SignInActivity
import com.example.personal_barter.SignActivityDir.UserInfoActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var timer: Timer? = null
    private lateinit var viewPager: ViewPager2
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.title = "Tool Bar"

        viewPager = findViewById<ViewPager2>(R.id.viewPager_event_banner)
        viewPager.adapter = MainViewPagerAdapter(this)
        viewPager.isUserInputEnabled = true

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_main_rank)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainRecyclerViewAdapter(getMainRankData())

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = "Page ${position + 1}"
        }.attach()

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout_main)
        refreshLayout.setOnRefreshListener {
            recyclerView.adapter = MainRecyclerViewAdapter(getMainRankData())
            refreshLayout.isRefreshing = false
        }
        job = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(3000)
                Log.d("coroutine", "dd")
                if (viewPager.currentItem + 1 < viewPager.adapter?.itemCount ?: 0)
                    viewPager.currentItem += 1
                else viewPager.currentItem = 0
            }
        }

//        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    Log.d("timer check", "dd")
                    if (viewPager.currentItem + 1 < viewPager.adapter?.itemCount ?: 0)
                        viewPager.currentItem += 1
                    else viewPager.currentItem = 0
                }
            }
        }, 3000, 3000)

        val bottomNaviBar = findViewById<BottomNavigationView>(R.id.bottomnavigation_main)
        bottomNaviBar.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.navigation_home ->{
                    true
                }
                R.id.navigation_sale -> {
                    val intent = Intent(this, SaleActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_purchase ->{
                    val intent = Intent(this, PurchaseActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    override fun onRestart() {
        super.onRestart()
        job = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(3000)
                Log.d("coroutine", "dd")
                if (viewPager.currentItem + 1 < viewPager.adapter?.itemCount ?: 0)
                    viewPager.currentItem += 1
                else viewPager.currentItem = 0
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.toolbar_search -> {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_search)
                dialog.show()
                dialog.findViewById<Button>(R.id.button_dialog_search).setOnClickListener {
                    Toast.makeText(this, "검색 이벤트 실행", Toast.LENGTH_SHORT).show()
                }
                return true
            }
            R.id.toolbar_info -> {
                if (!UserInfo.isSingIn()) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, UserInfoActivity::class.java)
                    startActivity(intent)
                }
                return true
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