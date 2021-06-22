package com.ekas.finhealkotlin.Models

import com.google.gson.annotations.SerializedName

data class UserStatusResponse(
    @SerializedName("status") var status:Boolean,
    @SerializedName("errorcode") var errorCode:Int,
    @SerializedName("message") var mdg:String,
    @SerializedName("data") var data:Data
)

data class Data(
    @SerializedName("message") var message:String,
    @SerializedName("is_Subscribed") var isSubscribed:Boolean,
    @SerializedName("can_apply") var canApply:Boolean,
    @SerializedName("existing_user") var existingUser:Boolean,
    @SerializedName("user_id") var userId:String,
    @SerializedName("mobile") var mobile:String,
    @SerializedName("email") var email:String,
    @SerializedName("profile_url") var profileUrl:String,
    @SerializedName("IsAadharVerified") var isAadharVerified:Boolean,
    @SerializedName("defaulter") var isDefaulter:String
)