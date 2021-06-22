package com.ekas.finhealkotlin.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ekas.finhealkotlin.Models.SendOtpRequest
import com.ekas.finhealkotlin.Models.TransactionStatusResponse
import com.ekas.finhealkotlin.Models.UserStatusResponse
import com.ekas.finhealkotlin.interfaces.API
import com.ekas.finhealkotlin.interfaces.APIResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class checkUserViewModel : ViewModel() {
    var apiResponse : APIResponseListener? = null

    fun getUserDetails(retrofit : Retrofit?){
        var retrofitService = retrofit?.create(API::class.java)
        var req = SendOtpRequest("9871288868", "59", true)
        val call: Call<UserStatusResponse?>? = retrofitService?.checkUserData(req)

        call?.enqueue( object : Callback<UserStatusResponse?> {
            override fun onResponse(call: Call<UserStatusResponse?>?, response: Response<UserStatusResponse?>?) {
                Log.d("RESULT11", response?.body()?.status.toString())
                if (response?.body()?.status == true)
                    apiResponse?.onSuccess(response?.body())
                if (response?.body()?.status==false){
                    apiResponse?.onFailure()
                }

            }

            override fun onFailure(call: Call<UserStatusResponse?>?, t: Throwable?) {
                apiResponse?.onFailure()
            }
        })
    }
}