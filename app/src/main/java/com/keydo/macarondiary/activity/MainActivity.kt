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


package com.keydo.macarondiary.activity



import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.keydo.macarondiary.fragment.DiaryFragment
import com.keydo.macarondiary.fragment.HomeFragment
import com.keydo.macarondiary.fragment.SettingFragment
import com.keydo.macarondiary.fragment.ShopFragment
import com.keydo.macarondiary.retrofitservice.RetrofitService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.keydo.macarondiary.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


lateinit var retrofitservice: RetrofitService
lateinit var retrofit: Retrofit
lateinit var gson:Gson

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

        bottomNavigation.setOnNavigationItemSelectedListener { item:MenuItem ->
            when(item.itemId){
                R.id.btn_fragment_gohome ->{
                    Log.d("Home","homebtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,homefragment).commitAllowingStateLoss();
                    }
                    true
                }
                R.id.btn_fragment_godiary ->{
                    Log.d("Home","diarybtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,diaryfragment).commitAllowingStateLoss();
                    }
                    true
                }
                R.id.btn_fragment_goshop ->{
                    Log.d("Home","shopbtn")
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_layout,shopfragment).commitAllowingStateLoss();
                    }
                    true
                }
                R.id.btn_fragment_gosetting ->{
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
        }
    }//Create End

    override fun onStop() {
        Log.d("Life","onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Life","onDestroy")
        super.onDestroy()
    }

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

    private fun retrofitInitilize(){
        //OkHttpInterceptor init
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        //Gson init
        //setLenient를 설정해주지 않으면 관련한 에러
        gson = GsonBuilder()
            .setLenient()
            .create()

        //Retrofit init
        retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.baseURL))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        //Retrofit Service 등록
        retrofitservice = retrofit.create(RetrofitService::class.java)
    }
}

