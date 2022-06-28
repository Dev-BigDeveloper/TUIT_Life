package com.example.tuitlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tuitlife.R
import com.example.tuitlife.databinding.FragmentStudentsBinding
import com.example.tuitlife.viewModel.DataModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding:FragmentStudentsBinding
    private val modelData: DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsBinding.inflate(inflater,container,false)

        modelData.message.value = 1

        val bundle = Bundle()

        binding.iqtdorliTalabalar.setOnClickListener{
            findNavController().navigate(R.id.talentedFragment)
        }

        binding.stipendiya.setOnClickListener{
            findNavController().navigate(R.id.scholarshipFragment)
        }

        binding.karyera.setOnClickListener{
            bundle.putString("key","4")
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        binding.chetELdaMagstr.setOnClickListener{
            bundle.putString("key","33")
            findNavController().navigate(R.id.webViewFragment,bundle)
        }

        binding.faxrliUstozlar.setOnClickListener{
            bundle.putString("key","ustoz")
            findNavController().navigate(R.id.webViewFragment,bundle)
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}