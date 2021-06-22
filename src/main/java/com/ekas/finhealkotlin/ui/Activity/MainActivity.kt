package com.ekas.finhealkotlin.ui.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.ekas.finhealkotlin.Models.SendMaintainenceResponse
import com.ekas.finhealkotlin.Models.TransactionStatusResponse
import com.ekas.finhealkotlin.Models.UserStatusResponse
import com.ekas.finhealkotlin.R
import com.ekas.finhealkotlin.interfaces.APIResponseListener
import com.ekas.finhealkotlin.network.APIClient
import com.ekas.finhealkotlin.viewModels.MaintainaceViewModel

class MainActivity : AppCompatActivity(), APIResponseListener {
  //  private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MaintainaceViewModel
    lateinit var videoPlayer : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MaintainaceViewModel()
        viewModel.apiResponse = this
        var retrofit = APIClient.getClient(this, "");

        videoPlayer = findViewById<VideoView>(R.id.videoPlayer);

        val uri =
            Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.splashbg)
        videoPlayer.setVideoURI(uri)
        videoPlayer.start()

        videoPlayer.setOnPreparedListener(){
            it.isLooping=false
        }

        videoPlayer.setOnCompletionListener() {
            viewModel.getResponse(retrofit)
        }

    }

    override fun onSuccess(response: TransactionStatusResponse?) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: UserStatusResponse?) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: SendMaintainenceResponse?) {
        var intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure() {
        Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.start()
    }
}

