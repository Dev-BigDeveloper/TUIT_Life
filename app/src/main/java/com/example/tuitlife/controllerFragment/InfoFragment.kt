package com.example.tuitlife.controllerFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuitlife.adapters.InfoAdapter
import com.example.tuitlife.databinding.FragmentInfoBinding
import com.example.tuitlife.models.Rektorat
import com.google.firebase.database.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "InfoFragment"

class InfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentInfoBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list: ArrayList<Rektorat>
    private lateinit var adapter:InfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        list = ArrayList()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("rektorat")

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
                Log.d(TAG, "onDataChange: ${list[0].name}")
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}