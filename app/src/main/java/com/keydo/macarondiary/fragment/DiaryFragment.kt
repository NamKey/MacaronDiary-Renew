package com.keydo.macarondiary.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.keydo.macarondiary.R
import com.keydo.macarondiary.activity.WritediaryActivity
import com.keydo.macarondiary.adapter.DiaryAdapter
import com.keydo.macarondiary.dataset.DiaryDataset
import com.keydo.macarondiary.responsetodataset
import com.keydo.macarondiary.retrofitdataset.ResponseDiaryList
import com.keydo.macarondiary.activity.retrofitservice
import kotlinx.android.synthetic.main.fragment_diary.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DiaryFragment : Fragment(), View.OnClickListener {
    var diarylist:List<ResponseDiaryList> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.title = "Diary"
        //ViewLinstener 등록
        viewListenerInitialize()

        //Auth 부분 구현 예상하여 처리
        val userid = "macaron"
        val reqdiarylistcall: Call<List<ResponseDiaryList>> = retrofitservice.reqDiaryList(userid)
        reqdiarylistcall.enqueue(object :Callback<List<ResponseDiaryList>>{
            override fun onFailure(call: Call<List<ResponseDiaryList>>, t: Throwable) {
                Log.d("Retrofit", t.message.toString())
                Log.d("Retrofit", call!!.toString())
                Toast.makeText(activity?.applicationContext,"불러오기 실패",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<ResponseDiaryList>>,
                responseList: Response<List<ResponseDiaryList>>) {
                if (responseList.isSuccessful){
                    Toast.makeText(activity?.applicationContext,"오늘도 1 마카롱",Toast.LENGTH_SHORT).show()
                    diarylist = responseList.body()!!
//                    Log.d("Retrofit", diarylist[0].respdiarytitle)
                    setAdapter(
                        responsetodataset(
                            diarylist
                        )
                    )
                }
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fab_gowritediary -> {
                startActivity(Intent(activity, WritediaryActivity::class.java))
            }
        }
    }

    private fun setAdapter(diarylist: List<DiaryDataset>){
        val set = AnimationSet(true)

        // Fade in animation
        val fadeIn: Animation = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 400
        fadeIn.fillAfter = true
        set.addAnimation(fadeIn)

        val wm = activity?.applicationContext?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay // in case of Activity
        val size = Point()
        display.getRealSize(size) // or getSize(size)


        // Slide up animation
        val slideUp: Animation = TranslateAnimation(0F, 0F, size.y.toFloat(), 0F)
        slideUp.interpolator = DecelerateInterpolator(4f)
        slideUp.duration = 500
        set.addAnimation(slideUp)

        // Set up the animation controller
        val controller = LayoutAnimationController(set, 0.2f)
        diary_recyclerview.layoutAnimation = controller

        val mAdapter = DiaryAdapter(activity?.applicationContext,diarylist)
        diary_recyclerview.adapter = mAdapter
        diary_recyclerview.layoutManager = LinearLayoutManager(activity?.applicationContext)
    }

    private fun viewListenerInitialize(){
        //fAB Initialize - findViewByID
        diary_swiperefreshview.setOnRefreshListener {
            Log.i("diaryfragment", "onRefresh called from SwipeRefreshLayout")
            //Auth 부분 구현 예상하여 처리
            val userid = "macaron"
            val reqdiarylistcall: Call<List<ResponseDiaryList>> = retrofitservice.reqDiaryList(userid)
            reqdiarylistcall.enqueue(object :Callback<List<ResponseDiaryList>>{
                override fun onFailure(call: Call<List<ResponseDiaryList>>, t: Throwable) {
                    Log.d("Retrofit", t.message.toString())
                    Log.d("Retrofit", call!!.toString())
                    Toast.makeText(activity?.applicationContext,"불러오기 실패",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<ResponseDiaryList>>,
                    responseList: Response<List<ResponseDiaryList>>) {
                    if (responseList.isSuccessful){
                        Toast.makeText(activity?.applicationContext,"오늘도 1 마카롱",Toast.LENGTH_SHORT).show()
                        diarylist = responseList.body()!!
//                    Log.d("Retrofit", diarylist[0].respdiarytitle)
                        setAdapter(
                            responsetodataset(
                                diarylist
                            )
                        )
                        diary_swiperefreshview.isRefreshing = false
                    }
                }
            })
        }
        fab_gowritediary.setOnClickListener(this)
    }
}