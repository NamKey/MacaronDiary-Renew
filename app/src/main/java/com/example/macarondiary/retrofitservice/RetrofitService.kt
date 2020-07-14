package com.example.macarondiary.retrofitservice

import com.example.macarondiary.retrofitdataset.ResponseDiary
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("/diary/")
    fun reqDiaryContent(
        @Query("review_id") review_id: Int,
        @Query("user_id") user_id: String,
        @Query("review_imagepath") review_imagepath: String,
        @Query("review_date") review_date: String,
        @Query("review_shopname") review_shopname: String,
        @Query("review_content") review_diarycontent: String
    ): Call<ResponseDiary>

    @FormUrlEncoded
    @POST("/test")
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