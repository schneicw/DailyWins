package com.schneider.dailywins.home

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.schneider.dailywins.R
import com.schneider.dailywins.data.DailyWin
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject


class DailyWinAdapter(private val itemClickCallback: ((DailyWin) -> Unit)?) : RecyclerView.Adapter<DailyWinAdapter.DailyWinViewHolder>() {

    var wins = listOf<DailyWin>()

    fun setData(wins: List<DailyWin>) {
        Log.d("DailyWinAdapter", "setData() called with: wins = $wins")
        this.wins = wins
        notifyItemRangeInserted(0, wins.size)
    }

    fun refreshData() {
        notifyDataSetChanged()
    }

    class DailyWinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = itemView.findViewById(R.id.date)
        val winTextView: TextView = itemView.findViewById(R.id.win_text)
        val winPhoto: ImageView = itemView.findViewById(R.id.image)
        val highlight: ImageView = itemView.findViewById(R.id.highlight)

        val constraint: ConstraintLayout = itemView.findViewById(R.id.constraint)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_win_item, parent, false)
        return DailyWinViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyWinViewHolder, position: Int) {
        var bigString = "${wins[position].winList?.get(0)} \n \n${wins[position].winList?.get(1)} \n \n${wins[position].winList?.get(2)}"
        holder.winTextView.text = bigString
        val outputFormatter: DateFormat = SimpleDateFormat("MM/dd/yyyy")
        holder.date.text =  outputFormatter.format(wins[position].date);
        if (wins[position].photoId == "null" || wins[position].photoId == null) {
            holder.winPhoto.setImageResource(R.drawable.sunset)
        } else {
            Picasso.get().load(wins[position].photoId).fit().into(holder.winPhoto)
        }

        if (wins[position].highlighted) {
            holder.highlight.setImageResource(R.drawable.ic_highlight)
        } else {
            holder.highlight.setImageResource(R.drawable.ic_not_highlight)
        }

        holder.highlight.setOnClickListener {
            wins[position].highlighted = !wins[position].highlighted
            itemClickCallback?.invoke(wins[position])
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return wins.size
    }

    fun getRandomColor(){
        val colorList = listOf("#8795E1","#E6ADA9","#AF99D8", "#F1C37F", "#A1E1DB", "#C887D3", "#F8EC7F", "#F3997D", "#97E39A")
//        val unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.background)
//        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
//        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(colorList.random()))
    }
}