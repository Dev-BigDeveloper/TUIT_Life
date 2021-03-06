package com.example.tuitlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tuitlife.R
import com.example.tuitlife.databinding.FragmentGiftedStudentsBinding
import com.example.tuitlife.viewModel.DataModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GiftedStudentsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentGiftedStudentsBinding
    private val modelData: DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        modelData.message.value = 3
        binding = FragmentGiftedStudentsBinding.inflate(inflater,container,false)
        binding.chatsNavigate.setOnClickListener{
            findNavController().navigate(R.id.signInFragment)
        }

        binding.infoTyu.setOnClickListener{
            findNavController().navigate(R.id.tuterFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GiftedStudentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}