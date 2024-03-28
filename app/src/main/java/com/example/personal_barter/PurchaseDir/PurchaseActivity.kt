package com.example.personal_barter.PurchaseDir

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personal_barter.MainActivityDir.MainActivity
import com.example.personal_barter.MainActivityDir.UserInfo
import com.example.personal_barter.R
import com.example.personal_barter.SaleDir.SaleActivity
import com.example.personal_barter.SignActivityDir.SignInActivity
import com.example.personal_barter.SignActivityDir.UserInfoActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PurchaseActivity : AppCompatActivity() {
    val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView_purchase_list) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        var recyclerViewAdapter = PurchaseRecyclerAdapter(listOf("dd", "aa", "cc"))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter

        val toolbar = findViewById<Toolbar>(R.id.toolbar_purchase)
        setSupportActionBar(toolbar)

        val navi = findViewById<BottomNavigationView>(R.id.bottomnavigation_purchase)
        navi.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_sale -> {
                    val intent = Intent(this, SaleActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
}