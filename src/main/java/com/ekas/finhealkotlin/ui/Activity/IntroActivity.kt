package com.ekas.finhealkotlin.ui.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ekas.finhealkotlin.Models.introModel
import com.ekas.finhealkotlin.R
import com.ekas.finhealkotlin.adapter.IntroPagerAdapter
import com.google.android.material.button.MaterialButton

class IntroActivity : AppCompatActivity() {
    private lateinit var viewPager : ViewPager
    private lateinit var tvSkip : TextView
    private lateinit var btnNext : MaterialButton
    private lateinit var btnGetStarted : MaterialButton
    private lateinit var adapter: IntroPagerAdapter
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (restorePrefData()){
            val intent = Intent(this, LandingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        viewPager = findViewById(R.id.viewPager)
        tvSkip = findViewById(R.id.tv_skip)
        btnNext = findViewById(R.id.submit)
        btnGetStarted = findViewById(R.id.getStarted)

        val list = ArrayList<introModel>()
        list.add(introModel("Instant Loan Approval","You can apply for Personal Loan Online and avail up to Rs 5 Lac as Direct Cash Transfer to Bank Account",R.drawable.servicetabp))
        list.add(introModel("Quick Loan Disbursal","Once approved, the sanctioned amount is credited to your bank account immediately.",R.drawable.onlineloan))
        list.add(introModel("Lowest Interest Rates","Interest rates range from 0% - 29.95% depending on risk profile of customer and product availed.",R.drawable.value))
        list.add(introModel("Minimal Documentation","Complete process is online with no physical paperwork.",R.drawable.userfrendly))


        Log.d("RESULT2222", ""+list.size)

        adapter = IntroPagerAdapter(this, list)

        viewPager.adapter = adapter

        tvSkip.setOnClickListener {
            viewPager.setCurrentItem(list.size - 1, false)
            btnNext.visibility = View.GONE
            btnGetStarted.visibility = View.VISIBLE
        }

        btnNext.setOnClickListener{
            position = viewPager.currentItem
            Log.d("Position", ""+ position)
            if (position < list.size) {
                position++
                viewPager.currentItem = position
            }

            if (position == (list.size)-1 ) {
                btnNext.visibility = View.GONE
                btnGetStarted.visibility = View.VISIBLE
                tvSkip.visibility = View.GONE
            }
        }


        btnGetStarted.setOnClickListener{
            savePrefsData()
            val intent = Intent(this, PermissionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun restorePrefData(): Boolean {
        val pref =
            applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        return pref.getBoolean("isIntroOpened", false)
    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isIntroOpened", true)
        editor.apply()
    }
}


