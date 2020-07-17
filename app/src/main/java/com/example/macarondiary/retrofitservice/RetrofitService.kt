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

    @GET("comments")
    fun getComment(
        @Query("postId") postId: Int
    ): Call<ResponseBody>

    @GET("diary")
    fun reqDiaryContent(
        @Query("review_id") reviewid: Int,
        @Query("user_id") userid: String
    ): Call<ResponseBody>



    @Headers("accept: application/json", "content-type: application/json")
    @POST("diary")
    fun reqdiaryWrite(
         @Body body: HashMap<String, Any>
    ): Call<ResponseBody>

    @Multipart
    @POST("diary")
    fun reqdiaryimageWrite(
        @Part diaryimages: List<MultipartBody.Part>,
        @Part("diaryhashmap") diaryhashmap: HashMap<String,Any>

//        @Part("hashmap") diaryhashmap: RequestBody
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