package com.example.macarondiary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.macarondiary.R
import com.example.macarondiary.dataset.DiaryDataset
import kotlinx.android.synthetic.main.diaryrecyclerview.view.*

class DiaryAdapter(val context: Context, val DiaryDataset: ArrayList<DiaryDataset>) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {
    class DiaryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent) {
        var tvDiarytitle:TextView = itemView.diary_title
        var tvShopname:TextView = itemView.diary_shopname
        var tvDiarydate:TextView = itemView.diary_date
        var tvDiarycontent:TextView = itemView.diary_content
        var ivDiaryimage:ImageView = itemView.diary_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryAdapter.DiaryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.diaryrecyclerview,parent, false)

        return DiaryViewHolder(itemView as ViewGroup)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.tvDiarytitle.text = DiaryDataset[position].diary_title
        holder.tvShopname.text = DiaryDataset[position].diary_shopname
        holder.tvDiarydate.text = DiaryDataset[position].diary_date
        holder.tvDiarycontent.text = DiaryDataset[position].diary_content
        Glide.with(context)
            .load(DiaryDataset[position].diary_imagepath)
            .override(60,60)
            .error(R.drawable.ic_sorry)
            .placeholder(R.drawable.ic_macaron)
            .into(holder.ivDiaryimage)
    }

    override fun getItemCount(): Int {
        return DiaryDataset.size
    }

}