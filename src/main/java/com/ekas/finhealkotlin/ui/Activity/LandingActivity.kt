package com.ekas.finhealkotlin.ui.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ekas.finhealkotlin.Models.SendMaintainenceResponse
import com.ekas.finhealkotlin.Models.TransactionStatusResponse
import com.ekas.finhealkotlin.Models.UserStatusResponse
import com.ekas.finhealkotlin.R
import com.ekas.finhealkotlin.interfaces.APIResponseListener
import com.ekas.finhealkotlin.network.APIClient
import com.ekas.finhealkotlin.viewModels.checkUserViewModel
import com.ekas.finhealkotlin.viewModels.transactionViewModel
import com.google.gson.Gson
import retrofit2.Retrofit
import java.util.*

class LandingActivity : AppCompatActivity(), APIResponseListener {
    private var mContext: Context? = null
    private var sstotal = 0
//    private val permanent_denied = "0"
    private var retrofit : Retrofit? = APIClient.getClientHeader(this,"")
    private lateinit var viewModel: transactionViewModel
    private lateinit var checkUserViewModel: checkUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        mContext = this@LandingActivity

        viewModel = transactionViewModel()
        viewModel.apiResponse=this
        viewModel.getTransactionResponse(retrofit)


    //    getRuntimePermissions()
    }

    // on Success of Transaction(Loan)List API..
    override fun onSuccess(response: TransactionStatusResponse?) {
        Log.d("MSGGGGGGGG", Gson().toJson(response).toString())
        sstotal = response?.data?.totalRecords!!
        checkUserViewModel = checkUserViewModel()
        checkUserViewModel.apiResponse=this
        checkUserViewModel.getUserDetails(retrofit)
    }

    override fun onSuccess(response: SendMaintainenceResponse?) {
        TODO("Not yet implemented")
    }

    override fun onFailure() {
        Toast.makeText(this, "Something Went Wrong. Try Again", Toast.LENGTH_LONG).show()
    }

    // on Success of CheckUserStatus API..
    override fun onSuccess(response: UserStatusResponse?) {
        Log.d("MSGGGG", Gson().toJson(response).toString())
        val sharedPreferences = applicationContext.getSharedPreferences("UserDetails", MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString("email",response?.data?.email)
        editor.putString("isDefaulter",response?.data?.isDefaulter)
        editor.putString("mobile",response?.data?.mobile)
        editor.putString("profileUrl",response?.data?.profileUrl)
        editor.putString("userId",response?.data?.userId)
        editor.putBoolean("canApply", response?.data?.canApply!!)
        editor.putBoolean("existingUser",response.data.existingUser)
        editor.putBoolean("isSubscribed",response.data.isSubscribed)
        editor.putBoolean("isAadharVerified",response.data.isAadharVerified)
        editor.apply()

        startActivity(Intent(this@LandingActivity, LoginActivity::class.java))
        finish()

//        if (response.data.existingUser){
//
//            if (sstotal>0){
//                val fragment: Fragment
//                val manager =
//                    supportFragmentManager
//                val transaction = manager.beginTransaction()
//                fragment = CheckStatusFragment()
//                transaction.add(android.R.id.content, fragment)
//                transaction.commit()
//            } else if (!response.data.canApply) {
//                if (response.data.isSubscribed){
//                    if (!TextUtils.isEmpty(response.data.profileUrl)){
//                        val fragment: Fragment
//                        val manager =
//                            supportFragmentManager
//                        val transaction = manager.beginTransaction()
//                        fragment = CheckStatusFragment()
//                        transaction.add(android.R.id.content, fragment)
//                        transaction.commit()
//                    } else {
//                        val fragment: Fragment
//                        val manager =
//                            supportFragmentManager
//                        val transaction = manager.beginTransaction()
//                        fragment = UploadDocs()
//                        transaction.add(android.R.id.content, fragment)
//                        transaction.commit()
//                    }
//                } else if (!response.data.isSubscribed) {
//                    val sharedPreferences = getSharedPreferences("LastFrag", MODE_PRIVATE)
//                    val LastFrag = sharedPreferences.getString("LastFrag", "")
//
//                    if (!TextUtils.isEmpty(LastFrag)) {
//                        var fragment: Fragment? = null
//                        val manager = supportFragmentManager
//                        val transaction = manager.beginTransaction()
//                        try {
//                            fragment =
//                                Class.forName("$packageName.ui.fragments.india.newpl.$LastFrag")
//                                    .newInstance() as Fragment
//                        } catch (e: IllegalAccessException) {
//                            e.printStackTrace()
//                        } catch (e: ClassNotFoundException) {
//                            e.printStackTrace()
//                        } catch (e: InstantiationException) {
//                            e.printStackTrace()
//                        }
//                        transaction.add(android.R.id.content, fragment!!)
//                        transaction.commit()
//                    } else {
//                        val fragment: Fragment
//                        val manager = supportFragmentManager
//                        val transaction = manager.beginTransaction()
//                        fragment = PersonalDetailFragment()
//                        transaction.add(android.R.id.content, fragment)
//                        transaction.commit()
//                    }
//                }
//            }
//
//        } else {
//            startActivity(Intent(this@LandingActivity, LoginActivity::class.java))
//            finish()
//        }
//

    }
}