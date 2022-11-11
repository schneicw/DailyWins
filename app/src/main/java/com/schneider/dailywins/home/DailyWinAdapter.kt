package com.schneider.dailywins.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schneider.dailywins.R
import com.schneider.dailywins.data.DailyWin

class DailyWinAdapter() : RecyclerView.Adapter<DailyWinAdapter.DailyWinViewHolder>() {

    var wins = listOf<DailyWin>()

    fun setData(wins: List<DailyWin>) {
        Log.d("DailyWinAdapter", "setData() called with: wins = $wins")
        this.wins = wins
        notifyItemRangeInserted(0, wins.size)
    }

    class DailyWinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val winTextView: TextView = itemView.findViewById(R.id.win_text)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_win_item, parent, false)
        return DailyWinViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyWinViewHolder, position: Int) {
        holder.winTextView.text = wins[position].winList?.get(1) ?: "no win"
    }

    override fun getItemCount(): Int {
        return wins.size
    }
}