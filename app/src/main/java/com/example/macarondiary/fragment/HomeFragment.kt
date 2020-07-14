package com.example.macarondiary.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.macarondiary.R
import com.example.macarondiary.retrofitdataset.ResponseDiary
import com.example.macarondiary.retrofitservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.macarondiary.MainActivity as MainActivity

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

        //activity retrofitservice
        retrofitservice.reqDiaryContent(
            1
            ,"key"
            ,"/key"
            ,"20200715"
            ,"macaron"
            ,"안녕하세요")
            .enqueue(object : Callback<ResponseDiary> {
                override fun onFailure(call: Call<ResponseDiary>?, t: Throwable) {
                    Log.e("retrofit",t.toString())
                }

                override fun onResponse(call: Call<ResponseDiary>?, response: Response<ResponseDiary>) {
                    Log.e("retrofit",response?.body().toString())
                }
            })
        view.findViewById<Button>(R.id.button_first).setOnClickListener {

        }
    }
}