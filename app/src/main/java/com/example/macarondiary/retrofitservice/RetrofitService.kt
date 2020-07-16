package com.example.macarondiary.retrofitservice

import com.example.macarondiary.retrofitdataset.ResponseDiary
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {

    @GET("comments")
    fun getComment(
        @Query("postId") postId: Int
    ): Call<ResponseBody>

    @GET("diary")
    fun reqDiaryContent(
        @Query("review_id") review_id: Int,
        @Query("user_id") user_id: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("diary")
    fun reqPost(
        @Field("id") id: String,
        @Field("pw") pw: String
    ): Call<ResponseDiary>


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