package com.example.macarondiary.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.macarondiary.R
import com.example.macarondiary.retrofitservice
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // get 방식


        // get 방식
        val comment: Call<ResponseBody> = retrofitservice.reqDiaryContent(1,"key","key/","20200714","macaron","contents")
        comment.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                try {
                    Log.v("Test", response.body()!!.string())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                call: Call<ResponseBody>,
                t: Throwable
            ) {
            }
        })
        //activity retrofitservice
//        retrofitservice.reqDiaryContent(
//            1
//            ,"key"
//            ,"/key"
//            ,"20200715"
//            ,"macaron"
//            ,"안녕하세요")
//            .enqueue(object : Callback<ResponseDiary> {
//                override fun onFailure(call: Call<ResponseDiary>?, t: Throwable) {
//                    Log.e("retrofit",t.toString())
//                }
//
//                override fun onResponse(call: Call<ResponseDiary>?, response: Response<ResponseDiary>) {
//                    Log.e("retrofit",response.body().toString())
//                }
//            })
        view.findViewById<Button>(R.id.button_first).setOnClickListener {

        }
    }
}