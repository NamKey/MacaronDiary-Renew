package com.keydo.macarondiary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.keydo.macarondiary.R
import com.keydo.macarondiary.dataset.SliderItem
import com.smarteist.autoimageslider.SliderViewAdapter


public class HomeSliderAdapter(private var context: Context) :
    SliderViewAdapter<HomeSliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<SliderItem> = ArrayList()
    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(
        viewHolder: SliderAdapterVH,
        position: Int
    ) {
        val sliderItem: SliderItem = mSliderItems[position]

        viewHolder.textViewDescription.text = sliderItem.imagedescription
        viewHolder.textViewAdress.text = sliderItem.imageadress
        Glide.with(viewHolder.itemView)
            .load(sliderItem.imageid)
            .fitCenter()
            .into(viewHolder.imageViewBackground)

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, "$position 번째 마카롱 가게", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_imageslider)
        var textViewDescription: TextView = itemView.findViewById(R.id.tv_imagedesc)
        var textViewAdress: TextView = itemView.findViewById(R.id.tv_imageadress)
    }

}