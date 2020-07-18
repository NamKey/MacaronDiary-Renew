package com.example.macarondiary.retrofitdataset

import com.google.gson.annotations.SerializedName

 class ResponseDiary(
     @SerializedName("diarypk")
     var respdiarypk:Int
     ,@SerializedName("diarytitle")
     var respdiarytitle:String
     ,@SerializedName("diarycontent")
     var respdiarycontent:String
     ,@SerializedName("diaryshopname")
     var respdiaryshopname:String
     ,@SerializedName("diarydate")
     var respdiarydate:String
     ,@SerializedName("diaryimagepatharray")
     var respimagepatharray:ArrayList<String>)
