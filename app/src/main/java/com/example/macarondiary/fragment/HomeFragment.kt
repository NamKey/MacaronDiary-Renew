package com.example.macarondiary.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.macarondiary.R
import com.example.macarondiary.WritediaryActivity
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
        val comment: Call<ResponseBody> = retrofitservice.reqDiaryContent(1,"key")
        comment.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                try {
                    Log.d("Retrofit", response.body()!!.string())
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

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            startActivity(Intent(activity, WritediaryActivity::class.java))
        }
    }
}