package com.ekas.finhealkotlin.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ekas.finhealkotlin.Models.SendMaintainenceResponse
import com.ekas.finhealkotlin.interfaces.API
import com.ekas.finhealkotlin.interfaces.APIResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MaintainaceViewModel : ViewModel() {

    var apiResponse : APIResponseListener? = null

    fun getResponse(retrofit : Retrofit?) {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://finhealcapital.in/app/admin/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

        var retrofitService = retrofit?.create(API::class.java)

        val call: Call<SendMaintainenceResponse?>? = retrofitService?.sendMainTainence()

        call?.enqueue( object : Callback<SendMaintainenceResponse?>{
            override fun onResponse(call: Call<SendMaintainenceResponse?>?, response: Response<SendMaintainenceResponse?>?) {
                Log.d("RESULT11", response?.body()?.app_message.toString())
                apiResponse?.onSuccess(response?.body())

            }

            override fun onFailure(call: Call<SendMaintainenceResponse?>?, t: Throwable?) {
                apiResponse?.onFailure()
            }
        })
    }


}





