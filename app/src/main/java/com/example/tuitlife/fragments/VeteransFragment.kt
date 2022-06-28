package com.example.tuitlife.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import com.example.tuitlife.adapters.InfoAdapter
import com.example.tuitlife.databinding.FragmentVeteransBinding
import com.example.tuitlife.models.Rektorat
import com.example.tuitlife.viewModel.DataModel
import com.google.firebase.database.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VeteransFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentVeteransBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list: ArrayList<Rektorat>
    private val modelData: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVeteransBinding.inflate(inflater,container,false)
        list = ArrayList()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("aqlliStduents")
        modelData.message.value = 2
        val client = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.hide()
            }
        }

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = client
        binding.webView.loadUrl("https://tuit.uz/bitiruvchilarimiz")

        binding.backImage.setOnClickListener{
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.webViewClient = client
            binding.webView.loadUrl("https://tuit.uz/bitiruvchilarimiz")
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VeteransFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}