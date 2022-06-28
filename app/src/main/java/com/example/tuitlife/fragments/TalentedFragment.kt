package com.example.tuitlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tuitlife.adapters.InfoAdapter
import com.example.tuitlife.databinding.FragmentTalentedBinding
import com.example.tuitlife.models.Rektorat
import com.example.tuitlife.viewModel.DataModel
import com.google.firebase.database.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TalentedFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentTalentedBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list: ArrayList<Rektorat>
    private lateinit var adapter: InfoAdapter
    private val modelData: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTalentedBinding.inflate(inflater, container, false)
        list = ArrayList()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("tatuCareer")
        modelData.message.value = 3
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                val children = snapshot.children
                for (child in children) {
                    if (child.value != null) {
                        binding.progressBar.hide()
                        val value = child.getValue(Rektorat::class.java)
                        if (value != null) {
                            list.add(value)
                        }
                        adapter = InfoAdapter(list)
                        binding.rv.adapter = adapter
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TalentedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}