package com.example.macarondiary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.macarondiary.R
import com.example.macarondiary.dataset.recycler_Dataset
import kotlinx.android.synthetic.main.recycler_textview.view.*

class MyAdapter(val context: Context, val myDataset: ArrayList<recycler_Dataset>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent) {
        var viewname:TextView = itemView.diary_title
        var viewshopname:TextView = itemView.diary_shopname
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_textview,parent, false)

        return MyViewHolder(itemView as ViewGroup)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewname.text = myDataset[position].diary_title
        holder.viewshopname.text = myDataset[position].diary_shopname
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

}