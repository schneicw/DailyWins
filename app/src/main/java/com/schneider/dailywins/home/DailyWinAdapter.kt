package com.schneider.dailywins.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schneider.dailywins.R
import com.schneider.dailywins.data.DailyWin

class DailyWinAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class DailyWinViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val winTextView: TextView = itemView.findViewById(R.id.win_text)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_win_item, parent, false)
        return DailyWinViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val win = getItem(position)
//        holder.bind(flower)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}