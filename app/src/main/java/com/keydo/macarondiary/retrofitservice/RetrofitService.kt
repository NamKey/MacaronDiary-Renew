package com.keydo.macarondiary.retrofitservice

import com.keydo.macarondiary.retrofitdataset.ResponseDiaryList
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {

    //DiaryFragment get diary List
    @Headers("Content-Type: application/json")
    @GET("diary/{userid}")
    fun reqDiaryList(
        @Path("userid") userid: String
    ): Call<List<ResponseDiaryList>>

    //DiaryActivity get diary detail
    @GET("diary/{userid}/{diarypk}")
    fun reqDiaryContent(
        @Path("diarypk") diarypk: Int,
        @Path("userid") userid: String
    ): Call<ResponseDiaryList>

    //WriteActivity write diary with multiple images
    @Multipart
    @POST("diary")
    fun reqdiaryimageWrite(
        @Part diaryimages: List<MultipartBody.Part>,
        @Part("diaryhashmap") diaryhashmap: HashMap<String,Any>
    ): Call<ResponseBody>

//    @PUT("/test")
//    fun reqPut(
//        @Field("id") id: String,
//        @Field("pw") pw: String
//    ): Call<ResponseDiary>
//
//
//    @DELETE("/test")
//    fun reqDel(
//        @Field("id") id: String,
//        @Field("pw") pw: String
//    ): Call<ResponseDiary>
}