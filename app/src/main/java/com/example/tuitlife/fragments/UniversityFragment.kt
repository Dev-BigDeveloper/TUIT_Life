package com.example.tuitlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tuitlife.R
import com.example.tuitlife.databinding.FragmentUniversityBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UniversityFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding:FragmentUniversityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityBinding.inflate(inflater,container,false)

        binding.rektorat.setOnClickListener {
            findNavController().navigate(R.id.infoFragment)
        }

        binding.nizom.setOnClickListener{
            findNavController().navigate(R.id.universitetNizomiFragment)
        }

        binding.goya.setOnClickListener{
            findNavController().navigate(R.id.studentsFragment3)
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UniversityFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}