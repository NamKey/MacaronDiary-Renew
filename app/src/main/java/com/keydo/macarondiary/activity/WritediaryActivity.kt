package com.keydo.macarondiary.activity


import android.net.Uri
import android.os.Build
import android.os.Bundle

import android.view.View

import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.keydo.macarondiary.R
import com.keydo.macarondiary.getImagePath

import com.keydo.macarondiary.retrofitservice.RetrofitService
import com.kroegerama.imgpicker.BottomSheetImagePicker
import kotlinx.android.synthetic.main.activity_writediary.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


lateinit var multiSelectionPicker:BottomSheetImagePicker

lateinit var al_ivmacaronphoto: ArrayList<ImageView>
lateinit var li_urimacaronphoto: List<Uri>

lateinit var reqwritediary_service: RetrofitService
lateinit var diaryimages: List<MultipartBody.Part>

class WritediaryActivity : AppCompatActivity()
    , BottomSheetImagePicker.OnImagesSelectedListener
    , View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writediary)

        viewLinstnerInitialize()

        //ArrayList of ImageView Initialize
        al_ivmacaronphoto = ArrayList()
        al_ivmacaronphoto.add(iv_macaron1)
        al_ivmacaronphoto.add(iv_macaron2)
        al_ivmacaronphoto.add(iv_macaron3)
        al_ivmacaronphoto.add(iv_macaron4)
        al_ivmacaronphoto.add(iv_macaron5)

        //선택한 이미지의 uri를 담은 ArrayList
        li_urimacaronphoto = arrayListOf()

        val writeactretrofit: Retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.baseURL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        reqwritediary_service = writeactretrofit.create(RetrofitService::class.java)
    }

    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        li_urimacaronphoto = uris
        diaryimages = arrayListOf()

        val selectedPhotocount = li_urimacaronphoto.count()

        //imageView initialize
        for (i in 1 .. al_ivmacaronphoto.count()){
            Glide
                .with(applicationContext)
                .load(R.drawable.ic_imagebtn_macaron)
                .fitCenter()
                .placeholder(R.drawable.ic_macaron)
                .into(al_ivmacaronphoto[i-1])
        }

        //imageView image assignment
        for (i in 1 .. selectedPhotocount){
            Glide
                .with(applicationContext)
                .load(li_urimacaronphoto[i-1])
                .apply(RequestOptions().transform(CenterCrop(),RoundedCorners(10)))
                .placeholder(R.drawable.ic_macaron)
                .into(al_ivmacaronphoto[i-1])
            //Image Compression 거친뒤에 upload 필요


            val diaryimage = File(
                getImagePath(
                    li_urimacaronphoto[i - 1],
                    applicationContext
                ).toString())

            val diaryreqBody = RequestBody.create(
                MediaType.parse("image/*")
                , diaryimage)

            (diaryimages as ArrayList<MultipartBody.Part>).add(
                MultipartBody.Part.createFormData("${i-1}"
                , diaryimage.name
                , diaryreqBody))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_diarywrite -> {

                // Diary의 내용을 HashMap에 입력
                val diaryhashmap:HashMap<String, Any> = HashMap()
                diaryhashmap["diarytitle"] = eT_diarytitle.text.toString()
                diaryhashmap["diarycontent"] = eT_diarycontent.text.toString()
                diaryhashmap["diaryshopname"] = eT_diaryshopname.text.toString()
                diaryhashmap["diarydate"] = LocalDate.now().toString()

//                Log.d("Retrofit",diaryhashmap.toString())
                //Service
                val testwritediaryreq: Call<ResponseBody> = reqwritediary_service.reqdiaryimageWrite(
                    diaryimages,
                    diaryhashmap)

                //Async Request
                testwritediaryreq.enqueue(object  : Callback<ResponseBody>{
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                        Log.d("Retrofit", "Callfail")
//                        Log.d("Retrofit", t.message.toString())
//                        Log.d("Retrofit", call!!.toString())
                        Toast.makeText(applicationContext,"문제가 있습니다",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                        Log.d("Retrofit", response.body()!!.string())
                        Toast.makeText(applicationContext,"1일 일카롱하세요^.^",Toast.LENGTH_SHORT).show()
                    }
                })
                finish()

            }//btn_writeend
            R.id.btn_diaryclose -> {
                finish()
            }

            R.id.ibtn_addphoto -> {
                pickMulti()
            }
        }
    }//Onclick End

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

    private fun viewLinstnerInitialize(){
        //ImageView/Btn ClickListner 등록
        btn_diaryclose.setOnClickListener(this)
        btn_diarywrite.setOnClickListener(this)
        ibtn_addphoto.setOnClickListener(this)
    }

}//Activity End



