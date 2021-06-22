package com.ekas.finhealkotlin.Models

import com.google.gson.annotations.SerializedName

data class TransactionStatusResponse(
    @SerializedName("status") var status:Boolean,
    @SerializedName("errorcode") var errorcode:Int,
    @SerializedName("message") var msg:String,
    @SerializedName("data") var data:Data1
)

data class Data1(
    @SerializedName("message") var msg:String,
    @SerializedName("total_record") var totalRecords:Int,
    @SerializedName("transaction_list") var list:ArrayList<TransactionList>
)

data class TransactionList(
    @SerializedName("transaction_id") var transactionId:String,
    @SerializedName("remarks") var remarks:String,
    @SerializedName("transaction_id_status") var transStatus:String,
    @SerializedName("no_of_emi") var no_of_emi:String,
    @SerializedName("is_approved") var isApproved:Boolean,
    @SerializedName("EMI_response") var emiResponseList: ArrayList<EMI_response_List>
)

data class EMI_response_List(
    @SerializedName("emi_amount") var amount:String,
    @SerializedName("repayment_amount") var repayment_amount:String,
    @SerializedName("late_payment_charges") var late_payment_charges:String,
    @SerializedName("payment_status") var payment_status:String,
    @SerializedName("Due_date") var due_date:String,
    @SerializedName("emi_transaction_id") var emi_transaction_id:String,

)



