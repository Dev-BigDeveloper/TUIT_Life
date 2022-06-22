package com.example.tuitlife.onboardingService

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitlife.databinding.ItemContainerOnboardingBinding

private const val TAG = "OnBoardingAdapter"

class OnBoardingAdapter(private var list: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.VH>() {
    inner class VH(private var binding: ItemContainerOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(onBoardingItem: OnBoardingItem) {
            binding.textTitle.text = onBoardingItem.title.toString()
            binding.imageOnBoarding.setImageResource(onBoardingItem.image!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemContainerOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}