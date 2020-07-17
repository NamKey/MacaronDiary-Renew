package com.example.macarondiary.retrofitdataset

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class RequestJSONDiary {
    @SerializedName("request")
    var request: JsonObject? = null

    @SerializedName("response")
    var response: JsonObject? = null
}