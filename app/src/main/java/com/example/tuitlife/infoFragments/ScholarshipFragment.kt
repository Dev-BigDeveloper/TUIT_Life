package com.example.tuitlife.infoFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tuitlife.R
import com.example.tuitlife.databinding.FragmentScholarshipBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ScholarshipFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding:FragmentScholarshipBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScholarshipBinding.inflate(inflater,container,false)

        binding.apply {
            val bundle = Bundle()
            atKrv.setOnClickListener{
                bundle.putString("key","1")
                findNavController().navigate(R.id.webViewFragment,bundle)
            }
            xorazmiy.setOnClickListener {
                bundle.putString("key","2")
                findNavController().navigate(R.id.webViewFragment,bundle)
            }

            prezidentAndBeriniyg.setOnClickListener{
                bundle.putString("key","3")
                findNavController().navigate(R.id.webViewFragment,bundle)
            }
            prezident.setOnClickListener{
                bundle.putString("key","president")
                findNavController().navigate(R.id.webViewFragment,bundle)
            }

            beruniy.setOnClickListener{
               bundle.putString("key","beruniy")
                findNavController().navigate(R.id.webViewFragment,bundle)
            }
        }

        val navController = findNavController()
//        navController.previousBackStackEntry?.savedStateHandle?.set("key", "")
//        navController.popBackStack()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScholarshipFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}