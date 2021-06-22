package com.ekas.finhealkotlin.interfaces

import com.ekas.finhealkotlin.Models.SendMaintainenceResponse
import com.ekas.finhealkotlin.Models.SendOtpRequest
import com.ekas.finhealkotlin.Models.TransactionStatusResponse
import com.ekas.finhealkotlin.Models.UserStatusResponse
import com.ekas.finhealkotlin.util.AppUrls

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @GET("v12/Maintainance/response")
    fun sendMainTainence(): Call<SendMaintainenceResponse?>

    @POST(AppUrls.API_TRANSACTION_LIST)
    fun getTransactionList(): Call<TransactionStatusResponse?>?

    @POST(AppUrls.API_USER_STATUS)
    fun checkUserData(@Body request: SendOtpRequest?): Call<UserStatusResponse?>?

}