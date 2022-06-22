package com.example.tuitlife


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.tuitlife.databinding.ActivitySpalshActiviityBinding

private const val TAG = "SplashActivity"

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpalshActiviityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalshActiviityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animation:Animation = AnimationUtils.loadAnimation(this,R.anim.alpha)
        binding.imageSplash.startAnimation(animation)

        val timer = object: CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e(TAG, "onTick: $millisUntilFinished")
            }

            override fun onFinish() {
                val intent = Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        timer.start()

    }
}