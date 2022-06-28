package com.example.tuitlife.infoFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.tuitlife.databinding.FragmentWebViewBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "WebViewFragment"

class WebViewFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentWebViewBinding

    @SuppressLint("UseRequireInsteadOfGet", "SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val url = "https://static.tuit.uz/uploads/1/woniqt7PVC5CEJZ8wH0cSAc0-ID3JNfa.pdf"
        val url1 = "https://static.tuit.uz/uploads/1/_ZOV0tOVKmwLqJ533FCGD2nvclvvCN0k.pdf"
        val url2 = "https://static.tuit.uz/uploads/1/JdOuDmDKuHdh9Nsolk0_YkRQxjkxXo-q.pdf"
        //val url4 = "https://drive.google.com/file/d/1-U4pA811V9ovZt_IZwg5U5u7cx_bG56F/view"


        val str = arguments!!.getString("key")
        binding.apply {
            val client = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.hide()
                }
            }
            when (str) {
                "1" -> {
                    binding.backImage.visibility = View.VISIBLE
                    binding.apply {
                        webView.settings.javaScriptEnabled = true
                        webView.webViewClient = client
                        webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$url")
                    }
                }
                "2" -> {
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$url1")
                }
                "3" -> {
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$url2")
                }
                "4" -> {
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://tuit.uz/qoshimcha-malumotlar")
                }
                "33" -> {
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://tuit.uz/xalqaro-aloqalar-bolimi")
                }
                "info" ->{
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://tuit.uz/umumiy-malumot")
                }
                "ustoz" ->{
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://tuit.uz/faxrli-ustozlarimiz")
                }
                "president" ->{
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://drive.google.com/file/d/1-U4pA811V9ovZt_IZwg5U5u7cx_bG56F/view")
                }
                "beruniy" ->{
                    binding.backImage.visibility = View.VISIBLE
                    webView.settings.javaScriptEnabled = true
                    webView.webViewClient = client
                    webView.loadUrl("https://drive.google.com/file/d/1fDZe24nv2TEfYqC6UdFW_jUMb9M9_KfR/view?usp=sharing")
                }
            }
            binding.backImage.setOnClickListener{
                binding.webView.settings.javaScriptEnabled = true
                binding.webView.webViewClient = client
                binding.webView.loadUrl("https://tuit.uz/faxrli-ustozlarimiz")
            }
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}