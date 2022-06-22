package com.example.tuitlife.controllerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.tuitlife.R
import com.example.tuitlife.databinding.FragmentControllerBinding
import com.example.tuitlife.fragments.GiftedStudentsFragment
import com.example.tuitlife.fragments.StudentsFragment
import com.example.tuitlife.fragments.UniversityFragment
import com.example.tuitlife.fragments.VeteransFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ControllerFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    private lateinit var binding:FragmentControllerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControllerBinding.inflate(inflater,container,false)
        menuService()
        return binding.root
    }

    private fun menuService() {
        val universityFragment = UniversityFragment()
        val fm: FragmentManager = parentFragmentManager
        fm.beginTransaction().add(R.id.containerFragment, universityFragment).commit()

        binding.bottomBar.onItemSelected = {
            when (it) {
                0 -> {
                    val fragment1 = UniversityFragment()
                    fm.beginTransaction().replace(R.id.containerFragment, fragment1).commit()
                }
                1 -> {
                    val fragment1 = StudentsFragment()
                    fm.beginTransaction().replace(R.id.containerFragment,fragment1).commit()
                }
                2 -> {
                    val fragment2 = VeteransFragment()
                    fm.beginTransaction().replace(R.id.containerFragment,fragment2).commit()
                }
                3 -> {
                    val fragment3 = GiftedStudentsFragment()
                    fm.beginTransaction().replace(R.id.containerFragment,fragment3).commit()
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ControllerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}