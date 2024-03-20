package com.example.personal_barter.MainActivityDir

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personal_barter.R

class MainRecyclerViewAdapter(private val itemList: List<MainRankData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_rank, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        if(holder is ViewHolder) holder.bind(item)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val text1 = itemView.findViewById<TextView>(R.id.textView_rankAndName)
        val text2 = itemView.findViewById<TextView>(R.id.textView_request)

        fun bind(item: MainRankData){
            text1.text = "${item.Rank}. ${item.itemName}"
            text2.text = "${item.exchangeRequestt}"
        }
    }

}