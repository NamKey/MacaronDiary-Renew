package com.example.macarondiary.adapter

import android.content.ContentResolver
import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
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

        //Base URL 형식 맞추기
        /* EX ) ./diaryimage/Screenshot_20200718-010633_Gallery.jpg
        * "./" 앞에 두 문자를 뺀 문자열 imagepath
        * server의 baseURL
        * baseURL(0,21) + imagepath(2,~) -> server_imagepath
        * */

        val thumbpath = context.resources.getString(R.string.baseURL).substring(0,21)+DiaryDataset[position].diary_imagepath[0].substring(2)
//        Log.d("Glide",thumbpath)
        // 대표 이미지
        Glide.with(context)
            .load(thumbpath)
            .override(60,60)
            .error(R.drawable.ic_sorry)
            .placeholder(R.drawable.ic_macaron)
            .into(holder.ivDiaryimage)
    }

    override fun getItemCount(): Int {
        return DiaryDataset.size
    }

}