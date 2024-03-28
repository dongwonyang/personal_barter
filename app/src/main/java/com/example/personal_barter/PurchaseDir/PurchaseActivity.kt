package com.example.personal_barter.PurchaseDir

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PurchaseActivity : AppCompatActivity() {
    val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView_purchase_list) }
    val database = Firebase.database
    val myRef = database.getReference("data")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        var barterList: List<String> = listOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val answer = mutableListOf<String>()
                val dataSnapshot = myRef.get().await()
                for (snapshot in dataSnapshot.children) {
                    val comment = snapshot.child("comment").getValue(String::class.java)
                    val imageUrl = snapshot.child("imageUrl").getValue(String::class.java)
                    answer.add("$comment $imageUrl")
                    Log.d("Data fetch clear", "$comment $imageUrl")
                }
                withContext(Dispatchers.Main) {
                    barterList = answer.map{it.split(" ").first()}
                    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                    recyclerView.adapter = PurchaseRecyclerAdapter(barterList)
                }
            } catch (e: Exception) {
                Log.e("getData", "Failed to fetch data: $e")
            }
        }



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val comment = snapshot.child("comment").getValue(String::class.java)
                    val imageUrl = snapshot.child("imageUrl").getValue(String::class.java)

                    Log.d("Real-time update", "Comment: $comment, Image URL: $imageUrl")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Real-time update failed", "Failed to update data: $databaseError")
            }
        })

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


    // 비동기로 데이터를 가져오는 Firebase의 addListenerForSingleValueEvent를 사용하고 있습니다.
    // 이 메서드는 데이터를 가져오는 데 시간이 걸리므로, answer에 데이터가 추가되기 전에 return answer 문이 실행될 수 있습니다.
    // 따라서 answer가 비어 있는 상태로 반환될 수 있습니다.
  fun getData(): List<String>{
      val answer = mutableListOf<String>()
      myRef.addListenerForSingleValueEvent(object : ValueEventListener {
          override fun onDataChange(dataSnapshot: DataSnapshot) {
              for (snapshot in dataSnapshot.children) {
                  val comment = snapshot.child("comment").getValue(String::class.java)
                  val imageUrl = snapshot.child("imageUrl").getValue(String::class.java)

                  answer.add("$comment $imageUrl")
                  Log.d("Data fetch clear", "$comment $imageUrl")
              }
          }

          override fun onCancelled(databaseError: DatabaseError) {
              Log.e("Data fetch failed", "Failed to fetch data: $databaseError")
          }
      })

      return answer
  }



}