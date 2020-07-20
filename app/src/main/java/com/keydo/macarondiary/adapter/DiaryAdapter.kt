package com.keydo.macarondiary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.keydo.macarondiary.R
import com.keydo.macarondiary.dataset.DiaryDataset
import kotlinx.android.synthetic.main.diaryrecyclerview.view.*

class DiaryAdapter(val context: Context?, val DiaryArray: List<DiaryDataset>) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    private var recyclerViewDataList : List<DiaryDataset> = DiaryArray

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryAdapter.DiaryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.diaryrecyclerview,parent, false)

        return DiaryViewHolder(itemView as ViewGroup)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(recyclerViewDataList[position],context)
    }

    override fun getItemCount(): Int {
        return DiaryArray.size
    }

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDiarytitle: TextView = itemView.diary_title
        var tvShopname:TextView = itemView.diary_shopname
        var tvDiarydate:TextView = itemView.diary_date
        var tvDiarycontent:TextView = itemView.diary_content
        var ivDiaryimage:ImageView = itemView.diary_image

        fun bind(itemDiary: DiaryDataset, context: Context?){
            tvDiarytitle.text = itemDiary.diary_title
            tvDiarycontent.text = itemDiary.diary_content
            tvDiarydate.text = itemDiary.diary_date
            tvShopname.text = itemDiary.diary_shopname

            //Base URL 형식 맞추기
            /* EX ) ./diaryimage/Screenshot_20200718-010633_Gallery.jpg
            * "./" 앞에 두 문자를 뺀 문자열 imagepath
            * server의 baseURL
            * baseURL(0,21) + imagepath(2,~) -> server_imagepath
            * */

            val thumbpath = context?.resources?.getString(R.string.baseURL)?.substring(0,21)+itemDiary.diary_imagepath.substring(2)
            // Thumbnail

            Glide.with(context!!)
                .load(thumbpath)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
                .override(300,300)
                .error(R.drawable.ic_macaron)
                .placeholder(R.drawable.ic_macaron)
                .into(ivDiaryimage)
        }
    }
}