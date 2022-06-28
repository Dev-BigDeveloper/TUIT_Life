package com.example.tuitlife.infoFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tuitlife.databinding.FragmentUniversityHistoryBinding
import com.google.firebase.database.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UniversityHistoryFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentUniversityHistoryBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityHistoryBinding.inflate(inflater, container, false)
            firebaseDatabase = FirebaseDatabase.getInstance()
            reference = firebaseDatabase.getReference("historyUniversity")

        val str =
            "Toshkent axborot texnologiyalari universiteti (qisqartmasi TATU; 2002-yilgacha Toshkent" +
                    " elektrotexnika aloqa instituti) — Toshkentdagi oliy oʻquv yurti boʻlib, informatika" +
                    " va axborot texnologiyasi, pochta xizmati, radioeshittirish va televideniya," +
                    " telekommunikatsiyalar boʻyicha yuqori malakali mutaxassislar tayyorlaydi. Toshkent" +
                    " elektrotexnika aloqa instituti nomi bilan 1955-yilda tashkil etilgan. 2003-yildan hozirgi " +
                    "davrga qadar Toshkent axborot texnologiyalari universiteti nomi bilan ataladi. Universitetda" +
                    " va filiallarida 11,000 dan ortiq talabalar taʼlim oladilar. Universitet tashkil qilingandan " +
                    "beri 30 mingdan ortiq yuqori malakali mutaxassis tayyorlandi. Universitetda 9 yoʻnalish boʻyicha" +
                    " bakalavrlar va besh mutaxassislik boʻyicha magistrlar tayyorlanadi."

        binding.imageHistory.setOnClickListener{
            reference.setValue(str)
        }

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progressBar.hide()
                val str1 = snapshot.value
                binding.textHistory.text = str1.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Internet o`chirilgan !!!", Toast.LENGTH_SHORT).show()
            }

        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UniversityHistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}