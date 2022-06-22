package com.example.tuitlife.chatsFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.tuitlife.adapters.MessageAdapterList
import com.example.tuitlife.databinding.FragmentMessageUsersBinding
import com.example.tuitlife.models.AppConstants
import com.example.tuitlife.models.Message
import com.example.tuitlife.models.UserMessagesService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "MessageFragmentUsers"

class MessageFragmentUsers : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentMessageUsersBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var referenceData: DatabaseReference
    private lateinit var messageAdapterList: MessageAdapterList
    private lateinit var referenceInfoMessage: DatabaseReference
    private lateinit var referenceNotification: DatabaseReference
    private lateinit var user: UserMessagesService
    private val referenceOnline = FirebaseDatabase.getInstance().getReference("isOnline")
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var topic = ""

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageUsersBinding.inflate(inflater,container,false)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
        referenceData = firebaseDatabase.getReference("data")
        referenceInfoMessage = firebaseDatabase.getReference("messageInfo")
        val referensOnline = firebaseDatabase.getReference("isOnline")
        referenceNotification = firebaseDatabase.getReference("notification")

        referenceNotification.child(currentUser!!.uid).setValue(0)

        Log.d(TAG, "onCreateView: ${currentUser.uid}")

        gotoPreviousScreen()

        user = arguments?.getSerializable("user") as UserMessagesService

        binding.name.text = user.displayName
        Picasso.get().load(user.photoUrl).into(binding.imageUser)

        binding.send.setOnClickListener {
            if (binding.editMessage.text == null){
                Toast.makeText(requireContext(), "No message", Toast.LENGTH_SHORT).show()
            }
            referenceNotification.child("${user.uid}").setValue(1)
            val mes = binding.editMessage.text.toString()
            var str = ""
            str = when {
                mes.length > 25 -> {
                    mes.substring(0,21) + "..."
                }
                str.length < 25 -> {
                    "$mes..."
                }
                else -> {
                    ""
                }
            }

            val simpleDataFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val date = simpleDataFormat.format(Date())
            val message = Message(mes, date, firebaseAuth.currentUser!!.uid, user.uid)
            val key = reference.push().key
            if (mes != "") {
                reference.child("${firebaseAuth.currentUser!!.uid}/messages/${user.uid!!}/$key")
                    .setValue(message)
                reference.child("${user.uid}/messages/${firebaseAuth.currentUser!!.uid}/$key")
                    .setValue(message)
            }

            if (str != ""){
                referenceInfoMessage.child("${user.uid}/$key").setValue(str)
            }

            binding.editMessage.text.clear()

            topic = "/topics/${user.uid}"

        }

        reference.child("${firebaseAuth.currentUser!!.uid}/messages/${user.uid}")
            .addValueEventListener(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = ArrayList<Message>()
                    val children = snapshot.children
                    for (child in children) {
                        val value = child.getValue(Message::class.java)
                        if (value != null) {
                            list.add(value)
                        }
                    }

                    messageAdapterList = MessageAdapterList(list, firebaseAuth.currentUser!!.uid)

                    binding.rvMessage.adapter = messageAdapterList
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        binding.layoutImage.setOnClickListener {
            val simpleDataFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val date = simpleDataFormat.format(Date())
            referenceData.setValue(date)
            findNavController().popBackStack()
        }

        referensOnline.child(user.uid!!).addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val n = snapshot.getValue(Int::class.java)
                if (n==1){
                    binding.onlineOff.text = "online"
                }else{
                    binding.onlineOff.text = "last seen recently"
                }
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        referenceNotification.child(currentUser!!.uid).setValue(0)
        referenceOnline.child(currentUser.uid).setValue(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (currentUser != null) {
            referenceOnline.child(currentUser.uid).setValue(0)
        }
    }

    private fun gotoPreviousScreen() {
        binding.imageToBack.setOnClickListener {
            val userIn = "Ishladi )"
            setFragmentResult(AppConstants.REQUEST_KEY, bundleOf(AppConstants.BUNDLE_KEY to userIn))
            findNavController().popBackStack()
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MessageFragmentUsers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}