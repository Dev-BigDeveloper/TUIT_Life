package com.example.tuitlife

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.tuitlife.databinding.ActivityMainBinding
import com.example.tuitlife.onboardingService.OnBoardingAdapter
import com.example.tuitlife.onboardingService.OnBoardingItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding11: ActivityMainBinding
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding11 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding11.root)

        setUpBoardingItems()
        setUponBoardingIndicators()
        setCurrentOnBoardIndicator(0)

        binding11.onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("ResourceAsColor")
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding11.backgroundConst.setBackgroundColor(Color.parseColor("#FFD4D4"))
                    }
                    1 -> {
                        binding11.backgroundConst.setBackgroundColor(Color.parseColor("#FFE9D4"))
                    }
                    2 -> {
                        binding11.backgroundConst.setBackgroundColor(Color.parseColor("#D4F5FF"))
                    }
                }
                super.onPageSelected(position)
            }
        })

        binding11.onBoardingViewPager.adapter = onBoardingAdapter
        binding11.onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardIndicator(position)
            }
        })
        binding11.buttonOnBoardingAction.setOnClickListener {
            if (binding11.onBoardingViewPager.currentItem + 1 < onBoardingAdapter.itemCount){
                binding11.onBoardingViewPager.currentItem = binding11.onBoardingViewPager.currentItem + 1
            }else{
                val intent = Intent(this,SecondActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setUpBoardingItems() {
        val list = ArrayList<OnBoardingItem>()
        val onBoardingItem = OnBoardingItem(
            R.drawable.splash1,
            "Universitetimmiz haqida umumiy malumotlar"
        )
        val onBoardingItem1 = OnBoardingItem(
            R.drawable.img_1,
            "Talabalarimmiz haqida malumot"
        )
        val onBoardingItem2 = OnBoardingItem(
            R.drawable.img_2,
            "Faxriylarimmiz haqida malumot"
        )

        val onBoardingItem3 = OnBoardingItem(
            R.drawable.people1,
            "Chet davlatlari bilan xamkorlar haqida malumot"
        )

        list.add(onBoardingItem)
        list.add(onBoardingItem1)
        list.add(onBoardingItem2)
        list.add(onBoardingItem3)

        onBoardingAdapter = OnBoardingAdapter(list)
    }

    private fun setUponBoardingIndicators() {
        val indicators: Array<ImageView?> = arrayOfNulls(onBoardingAdapter.itemCount)
        val params = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.onboarding_indicator_active
                )
            )
            indicators[i]!!.layoutParams = params
            binding11.layoutInBoardingsIndicators.addView(indicators[i])
        }
    }

    private fun setCurrentOnBoardIndicator(index: Int) {
        val childCount = binding11.layoutInBoardingsIndicators.childCount
        for (i in 0 until childCount) {
            val imageView = binding11.layoutInBoardingsIndicators.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_inactive
                    )
                )
            }
        }
        if (index == onBoardingAdapter.itemCount - 1){
            binding11.buttonOnBoardingAction.text = "Start"
        }else{
            binding11.buttonOnBoardingAction.text = "Next"
        }
    }
}