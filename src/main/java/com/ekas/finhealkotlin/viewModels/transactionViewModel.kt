package com.ekas.finhealkotlin.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ekas.finhealkotlin.Models.SendMaintainenceResponse
import com.ekas.finhealkotlin.Models.TransactionStatusResponse
import com.ekas.finhealkotlin.interfaces.API
import com.ekas.finhealkotlin.interfaces.APIResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class transactionViewModel : ViewModel() {
    var apiResponse : APIResponseListener? = null

    fun getTransactionResponse(retrofit : Retrofit?){
        var retrofitService = retrofit?.create(API::class.java)
        val call: Call<TransactionStatusResponse?>? = retrofitService?.getTransactionList()

        call?.enqueue( object : Callback<TransactionStatusResponse?> {
            override fun onResponse(call: Call<TransactionStatusResponse?>?, response: Response<TransactionStatusResponse?>?) {
                Log.d("RESULT11", response?.body()?.status.toString())
                if (response?.body()?.status == true)
                apiResponse?.onSuccess(response?.body())
                if (response?.body()?.status==false){
                    apiResponse?.onFailure()
                }

            }

            override fun onFailure(call: Call<TransactionStatusResponse?>?, t: Throwable?) {
                apiResponse?.onFailure()
            }
        })
    }
}