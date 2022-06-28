package com.example.tuitlife.controllerFragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.tuitlife.R
import com.example.tuitlife.databinding.FragmentControllerBinding
import com.example.tuitlife.fragments.GiftedStudentsFragment
import com.example.tuitlife.fragments.StudentsFragment
import com.example.tuitlife.fragments.UniversityFragment
import com.example.tuitlife.fragments.VeteransFragment
import com.example.tuitlife.viewModel.DataModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "ControllerFragment"

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

    private lateinit var binding: FragmentControllerBinding
    private val modelData: DataModel by activityViewModels()
    private var bool = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControllerBinding.inflate(inflater, container, false)

        modelData.message.observe(activity as LifecycleOwner) { it1 ->
            bool = it1
        }

        binding.bottomBar.setOnItemReselectedListener {
            Log.d(TAG, "onCreateView: setOnItemReselectedListener it -> $it")
        }

        binding.bottomBar

        menuService()

        return binding.root
    }

    private fun menuService() {
        val universityFragment = UniversityFragment()
        val studentsFragment = StudentsFragment()
        val giftedStudentsFragment = GiftedStudentsFragment()
        val fm: FragmentManager = parentFragmentManager
        when (bool) {
            0 -> {
                fm.beginTransaction().replace(R.id.containerFragment, universityFragment).commit()
                binding.bottomBar.itemActiveIndex = 0
            }
            1 -> {
                fm.beginTransaction().replace(R.id.containerFragment, studentsFragment).commit()
                binding.bottomBar.itemActiveIndex = 1
            }
            3 -> {
                fm.beginTransaction().replace(R.id.containerFragment,giftedStudentsFragment).commit()
                binding.bottomBar.itemActiveIndex = 3
            }
        }
        binding.bottomBar.onItemSelected = { it2 ->
            when (it2) {
                0 -> {
                    modelData.message.value = 0
                    val fragment1 = UniversityFragment()
                    fm.beginTransaction().replace(R.id.containerFragment, fragment1)
                        .commit()
                }
                1 -> {
                    modelData.message.value = 1
                    val fragment1 = StudentsFragment()
                    fm.beginTransaction().replace(R.id.containerFragment, fragment1)
                        .commit()
                }
                2 -> {
                    modelData.message.value = 2
                    val fragment2 = VeteransFragment()
                    fm.beginTransaction().replace(R.id.containerFragment, fragment2)
                        .commit()
                }
                3 -> {
                    modelData.message.value = 3
                    val fragment3 = GiftedStudentsFragment()
                    fm.beginTransaction().replace(R.id.containerFragment, fragment3)
                        .commit()
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