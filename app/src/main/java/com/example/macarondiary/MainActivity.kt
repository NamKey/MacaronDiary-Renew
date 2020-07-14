/* 진행상황
1. View
    - Fragment
    - BottomNavigationView
2. Function

3. Communication

*/

/* 특이사항
2020.07.08->bottomNavigation object의 setOnNavigationItemSeletected Method를 BottomNavigaionView의 OnNavigationSelected Method로 Overriding

*/


package com.example.macarondiary


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.macarondiary.fragment.DiaryFragment
import com.example.macarondiary.fragment.HomeFragment
import com.example.macarondiary.fragment.SettingFragment
import com.example.macarondiary.fragment.ShopFragment
import com.example.macarondiary.retrofitdataset.ResponseDiary
import com.example.macarondiary.retrofitservice.RetrofitService

import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var retrofitservice: RetrofitService
lateinit var retrofit: Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        //Retrofit 초기설정
        retrofitInitilize()

        //View 선언
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        //Fragment 선언
        val homefragment = HomeFragment()
        val diaryfragment = DiaryFragment()
        val shopfragment = ShopFragment()
        val settingfragment = SettingFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_layout,homefragment).commitAllowingStateLoss();
        }

        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {item:MenuItem ->
            when(item.itemId){
                R.id.btn_fragment_gohome->{
                    Log.d("Home","homebtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,homefragment).commitAllowingStateLoss();
                    }
                    true
                }
                R.id.btn_fragment_godiary->{
                    Log.d("Home","diarybtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,diaryfragment).commitAllowingStateLoss();
                    }
                    true
                }
                R.id.btn_fragment_goshop->{
                    Log.d("Home","shopbtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,shopfragment).commitAllowingStateLoss();
                    }
                    true
                }
                R.id.btn_fragment_gosetting->{
                    Log.d("Home","settingbtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,settingfragment).commitAllowingStateLoss();
                    }
                    true
                }
                else -> {
                    false
                }
            }
        })
    }//CreateView End

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun retrofitInitilize(){
        //Retrofit object 생성
        retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.baseURL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Retrofit Service 등록
        retrofitservice = retrofit.create(RetrofitService::class.java)
    }
}