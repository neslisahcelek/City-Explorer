package com.example.explorethecity.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.explorethecity.R
import com.example.explorethecity.databinding.FragmentChangeCityBinding
import com.example.explorethecity.databinding.FragmentProfileBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ChangeCityFragment : Fragment() {
    private var _binding: FragmentChangeCityBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var sp: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangeCityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        db = Firebase.firestore
        sp = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)


        return root
    }


}