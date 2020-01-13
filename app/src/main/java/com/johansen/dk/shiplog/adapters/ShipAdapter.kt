package com.johansen.dk.shiplog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.Note
import com.johansen.dk.shiplog.data.Ship
import kotlinx.android.synthetic.main.note_recycler_card.view.*
import kotlinx.android.synthetic.main.ship_recycler_card.view.*


class ShipAdapter(private val items : MutableList<Ship>, private val context: Context) : RecyclerView.Adapter<ShipViewHolder>() {

    // Gets the number of items in list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
        return ShipViewHolder(LayoutInflater.from(context).inflate(R.layout.ship_recycler_card, parent, false))
        }

    override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
        val temp = items[position]
        //holder.time?.text = temp.time
        holder.shipCard_name.text=temp.name
        //holder.shipCard_image.setImageResource()
    }
}

class ShipViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    //val time = view.note_date
    val shipCard_name = view.shipCard_name
    val shipCard_image = view.shipCard_image
}

