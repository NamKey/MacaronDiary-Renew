package com.example.macarondiary


import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button


import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.kroegerama.imgpicker.BottomSheetImagePicker
import com.kroegerama.imgpicker.ButtonType
import com.example.macarondiary.WritediaryActivity.*
import kotlinx.android.synthetic.main.activity_writediary.*

lateinit var multiSelectionPicker:BottomSheetImagePicker
lateinit var ibtnaddphoto:ImageButton
lateinit var btndiarywrite:Button
lateinit var btndiaryclose:Button

lateinit var iv_macaronphoto1:ImageView
lateinit var iv_macaronphoto2:ImageView
lateinit var iv_macaronphoto3:ImageView
lateinit var iv_macaronphoto4:ImageView
lateinit var iv_macaronphoto5:ImageView

lateinit var al_ivmacaronphoto: ArrayList<ImageView>
lateinit var li_urimacaronphoto: List<Uri>

class WritediaryActivity : AppCompatActivity()
    , BottomSheetImagePicker.OnImagesSelectedListener
    , View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writediary)

        viewInitialize()

        //ArrayList of ImageView Initialize
        al_ivmacaronphoto = ArrayList()
        al_ivmacaronphoto.add(iv_macaronphoto1)
        al_ivmacaronphoto.add(iv_macaronphoto2)
        al_ivmacaronphoto.add(iv_macaronphoto3)
        al_ivmacaronphoto.add(iv_macaronphoto4)
        al_ivmacaronphoto.add(iv_macaronphoto5)
        li_urimacaronphoto = arrayListOf()
    }

    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        li_urimacaronphoto = uris

        val selectedPhotocount = li_urimacaronphoto.count()
        for (i in 1 .. al_ivmacaronphoto.count()){
            Glide
                .with(applicationContext)
                .load(R.drawable.ic_imagebtn_macaron)
                .fitCenter()
                .placeholder(R.drawable.ic_macaron)
                .into(al_ivmacaronphoto[i-1])
        }

        for (i in 1 .. selectedPhotocount){
            Glide
                .with(applicationContext)
                .load(li_urimacaronphoto[i-1])
                .override(60,60)
                .placeholder(R.drawable.ic_macaron)
                .into(al_ivmacaronphoto[i-1])
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_diarywrite -> {
                //retrofit service 동작
                //gson?json 
            }

            R.id.btn_diaryclose -> {
                finish()
            }

            R.id.ibtn_addphoto -> {
                pickMulti()
            }
        }
    }

    private fun pickMulti() {
        BottomSheetImagePicker.Builder(getString(R.string.file_provider))
            .columnSize(R.dimen.columnSize)
            .multiSelect(1, 5)
            .multiSelectTitles(
                R.plurals.pick_multi,
                R.plurals.pick_multi_more,
                R.string.pick_multi_limit
            )
            .requestTag("multi")
            .show(supportFragmentManager)
    }

    private fun viewInitialize(){
        //ImageView/Btn Initialize - findViewByID

        btndiaryclose = findViewById(R.id.btn_diaryclose)
        btndiarywrite = findViewById(R.id.btn_diarywrite)
        ibtnaddphoto = findViewById(R.id.ibtn_addphoto)

        iv_macaronphoto1 = findViewById(R.id.iv_macaron1)
        iv_macaronphoto2 = findViewById(R.id.iv_macaron2)
        iv_macaronphoto3 = findViewById(R.id.iv_macaron3)
        iv_macaronphoto4 = findViewById(R.id.iv_macaron4)
        iv_macaronphoto5 = findViewById(R.id.iv_macaron5)

        btndiaryclose.setOnClickListener(this)
        btndiarywrite.setOnClickListener(this)
        ibtnaddphoto.setOnClickListener(this)
    }
}

