package com.example.macarondiary.retrofitservice

import com.example.macarondiary.retrofitdataset.RequestJSONDiary
import com.example.macarondiary.retrofitdataset.ResponseDiary
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {
    //DiaryFragment get diary List
//    @GET("diary")
//    fun reqDiaryList(
//        @Query("userid") userid: String
//    ): Call<List<ResponseBody>>
//
//    //DiaryActivity get diary detail
//    @GET("diary")
//    fun reqDiaryContent(
//        @Query("diarypk") diarypk: Int,
//        @Query("userid") userid: String
//    ): Call<ResponseBody>

    //DiaryFragment get diary List
    @GET("diary/{userid}")
    fun reqDiaryList(
        @Query("userid") userid: String
    ): Call<List<ResponseDiary>>

    //DiaryActivity get diary detail
    @GET("diary/{userid}/{diarypk}")
    fun reqDiaryContent(
        @Path("diarypk") diarypk: Int,
        @Path("userid") userid: String
    ): Call<ResponseDiary>

    //WriteActivity write diary with multiple images
    @Multipart
    @POST("diary")
    fun reqdiaryimageWrite(
        @Part diaryimages: List<MultipartBody.Part>,
        @Part("diaryhashmap") diaryhashmap: HashMap<String,Any>
    ): Call<ResponseBody>

//    @FormUrlEncoded
//    @Headers("accept: application/json", "content-type: application/json")
//    @POST("diary")
//    fun reqdiaryWrite(
//         @Body body: ResponseDiary
//    ): Call<ResponseDiary>


//    @FormUrlEncoded
//    @Headers("accept: application/json", "content-type: application/json")
//    @POST("diary")
//    fun reqdiaryWrite(
//        @Field("diary_title") diarytitle: String,
//        @Field("diary_shopname") diaryshopname: String,
//        @Field("diary_date") diarydate: String,
//        @Field("diary_content") diarycontent: String,
//        @Field("diary_imagepath") diaryimagepath: String
//    ): Call<ResponseDiary>


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