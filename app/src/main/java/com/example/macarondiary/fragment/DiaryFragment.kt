package com.example.macarondiary.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macarondiary.R
import com.example.macarondiary.WritediaryActivity
import com.example.macarondiary.adapter.DiaryAdapter
import com.example.macarondiary.dataset.DiaryDataset
import com.example.macarondiary.retrofitdataset.ResponseDiary
import com.example.macarondiary.retrofitservice
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_writediary.*
import kotlinx.android.synthetic.main.fragment_diary.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DiaryFragment : Fragment(), View.OnClickListener {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var datalist:ArrayList<DiaryDataset> = arrayListOf()
    var imagelist:ArrayList<String> = arrayListOf()
    var diarylist:List<ResponseDiary> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLinstnerInitialize();

        viewManager = LinearLayoutManager(view.context)
        viewAdapter = DiaryAdapter(view.context,datalist)

        //test code
//        val imagepath1 = "./diaryimage/Screenshot_20200718-010645_Gallery.jpg";
//        val imagepath2 = "./diaryimage/Screenshot_20200718-010642_Gallery.jpg";
//        val imagepath3 = "./diaryimage/Screenshot_20200718-010638_Gallery.jpg";
//        imagelist.add(imagepath1)
//        imagelist.add(imagepath2)
//        imagelist.add(imagepath3)
//        val diary1 = DiaryDataset(diary_title = "Macaron1",diary_shopname = "shop1",diary_date = "020202",diary_content = "Hello",diary_imagepath = imagelist)
//        val diary2 = DiaryDataset(diary_title = "Macaron2",diary_shopname = "shop2",diary_date = "020202",diary_content = "Hello",diary_imagepath = imagelist)
//        val diary3 = DiaryDataset(diary_title = "Macaron3",diary_shopname = "shop3",diary_date = "020202",diary_content = "Hello",diary_imagepath = imagelist)
//        datalist.add(diary1)
//        datalist.add(diary2)
//        datalist.add(diary3)
        //test code end

        //Auth 부분 구현 예상하여 처리
        val userid = "macaron"
        val reqdiarylistcall: Call<List<ResponseDiary>> = retrofitservice.reqDiaryList(userid)
        reqdiarylistcall.enqueue(object :Callback<List<ResponseDiary>>{
            override fun onFailure(call: Call<List<ResponseDiary>>, t: Throwable) {
                Log.d("Retrofit", t.message.toString())
                Log.d("Retrofit", call!!.toString())
                Toast.makeText(activity?.applicationContext,"불러오기 실패",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<ResponseDiary>>,
                response: Response<List<ResponseDiary>>) {
                if (response.isSuccessful){
                    Toast.makeText(activity?.applicationContext,"불러오기 성공",Toast.LENGTH_SHORT).show()
                    diarylist = response.body()!!
                }
            }
        })


//        val userid = "macaron"
//        val reqdiarylistcall: Call<List<ResponseDiary>> = retrofitservice.reqDiaryList(userid)
//        reqdiarylistcall.enqueue(object :Callback<List<ResponseDiary>>{
//            override fun onFailure(call: Call<List<ResponseDiary>>, t: Throwable) {
//                Log.d("Retrofit",call.toString())
//                Log.d("Retrofit",t.message.toString())
//                Toast.makeText(activity?.applicationContext,"불러오기 실패",Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(
//                call: Call<List<ResponseDiary>>,
//                response: Response<List<ResponseDiary>>) {
//                if (response.isSuccessful){
//                    Toast.makeText(activity?.applicationContext,"불러오기 성공",Toast.LENGTH_SHORT).show()
//                    diarylist = response.body()!!
//                }
//            }
//        })

        diary_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fab_gowritediary -> {
                startActivity(Intent(activity, WritediaryActivity::class.java))
            }


        }
    }

    private fun viewLinstnerInitialize(){
        //fAB Initialize - findViewByID
        fab_gowritediary.setOnClickListener(this)

    }
}