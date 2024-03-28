package com.example.personal_barter.PurchaseDir

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personal_barter.R

class PurchaseRecyclerAdapter(private val itemList: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchase_list, parent, false)
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
        val text = itemView.findViewById<TextView>(R.id.textView_item_purchaseList)

        fun bind(item: String){
            text.text = item
        }
    }

}