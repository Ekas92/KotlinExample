package com.ekas.finhealkotlin.Models

import com.google.gson.annotations.SerializedName

data class SendOtpRequest(
    @SerializedName("mobile") var mobile:String,
    @SerializedName("app_ver") var appVersion:String,
    @SerializedName("status") var status:Boolean,

)