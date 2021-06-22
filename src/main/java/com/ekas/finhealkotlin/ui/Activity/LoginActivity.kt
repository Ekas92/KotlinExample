package com.ekas.finhealkotlin.ui.Activity

import android.content.Context
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.ekas.finhealkotlin.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {
    private lateinit var img:ImageView
    private lateinit var cbTerms: CheckBox
    private lateinit var phone_layout: TextInputLayout
    private lateinit var inputPhone: TextInputEditText

    private lateinit var ll_otp: LinearLayout

    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mContext = this@LoginActivity

        img = findViewById(R.id.img)
        phone_layout = findViewById(R.id.phone_layout)
        inputPhone = findViewById(R.id.input_phone)
        cbTerms = findViewById(R.id.cbTerms)
        ll_otp = findViewById(R.id.ll_otp)

        termsNCondition()


        // Automatically set +91 as prefix and user's number
        inputPhone.setText("+91")
        Selection.setSelection(inputPhone.text, inputPhone.text?.length!!)

        inputPhone.doAfterTextChanged {
            if (!it.toString().startsWith("+91")){
                // Automatically set +91 as prefix and user's number
                inputPhone.setText("+91")
                Selection.setSelection(inputPhone.text, inputPhone.text?.length!!)
            }
        }

        phone_layout.suffixTextView.setOnClickListener {
            ll_otp.visibility = View.VISIBLE
            img.setImageResource(R.drawable.otpverify)
        }

    }

    private var color = 0

    private fun termsNCondition() {
        try {
            // Create Terms-Conditions text view as multicolor and click-able.
            val textTermsAgree = resources.getString(R.string.apply_t_n_c)
            val termsStartPos =
                textTermsAgree.indexOf(resources.getString(R.string.terms_n_conditions))
            val termsEndPos =
                termsStartPos + resources.getString(R.string.terms_n_conditions).length
            val startIndex2 = textTermsAgree.indexOf(resources.getString(R.string.privacy))
            val endIndex2 = startIndex2 + resources.getString(R.string.privacy).length
            val wordToSpan = SpannableString(textTermsAgree)
            color = resources.getColor(R.color.greenDarkColor)
            wordToSpan.setSpan(
                ForegroundColorSpan(color),
                termsStartPos,
                termsEndPos,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            wordToSpan.setSpan(
                ForegroundColorSpan(color),
                startIndex2,
                endIndex2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            wordToSpan.setSpan(
                clickableSpanTerms,
                termsStartPos,
                termsEndPos,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            wordToSpan.setSpan(
                clickableSpanPrivacy,
                startIndex2,
                endIndex2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            cbTerms.setText(wordToSpan)
            cbTerms.setMovementMethod(LinkMovementMethod.getInstance())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val clickableSpanTerms: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            Toast.makeText(this@LoginActivity, "Terms & Condition", Toast.LENGTH_LONG).show()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = color // Set text color
            ds.isUnderlineText = false // Set to false to remove underline
        }
    }

    private val clickableSpanPrivacy: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            Toast.makeText(this@LoginActivity, "Privacy Policy", Toast.LENGTH_LONG).show()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = color // Set text color
            ds.isUnderlineText = false // Set to false to remove underline
        }
    }
}

