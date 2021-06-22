package com.ekas.finhealkotlin.interfaces

import com.ekas.finhealkotlin.Models.SendMaintainenceResponse
import com.ekas.finhealkotlin.Models.TransactionStatusResponse
import com.ekas.finhealkotlin.Models.UserStatusResponse

interface APIResponseListener {

    abstract fun onSuccess(response: SendMaintainenceResponse?)

    fun onSuccess(response: TransactionStatusResponse?)

    fun onSuccess(response: UserStatusResponse?)

    fun onFailure()
}