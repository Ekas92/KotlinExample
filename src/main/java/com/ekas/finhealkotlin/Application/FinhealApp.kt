package com.ekas.finhealkotlin.Application

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager

class FinhealApp : Application() {

    companion object {

        var USER_TOKEN: String? = null
        var USER_ID: String? = null
        var USER_NAME: String? = null
        var USER_EMAIL: String? = null
        var USER_PHONE: String? = null
        var USER_DOB: String? = null
        var USER_GENDER: String? = null
        var USER_LOC: String? = null
        var CIBIL_SCORE = ""
        var Loan_ID: String? = null
        var PARTNER_refer_code = "Sample001"
        var s3 = ""
        var versionCode = 0
        var USER_SUBSCRIBED = false
        var CAN_APPLY = false
        var loanIds = 0
        var paytmamt = ""
        var paytmTxnToken = ""
        var paymentMode = "paytm"
        var paytmOrderId = ""
        var payment_type = ""
        var EMP_TYPE = ""

        var MY_FORMAT_USER_DOB = ""

        var Path_value = ""

        private val instance: FinhealApp? = null
        private val activityVisible = false


        fun getInstance(): FinhealApp? {
            return instance
        }


        fun getUserToken(context: Context?): String? {
            if (USER_TOKEN == null || USER_TOKEN?.length == 0) {
                val preferences = context?.getSharedPreferences("UserDetails", MODE_PRIVATE)
                USER_TOKEN = preferences?.getString("USER_TOKEN", "")
            }
            return USER_TOKEN
        }

    }



}