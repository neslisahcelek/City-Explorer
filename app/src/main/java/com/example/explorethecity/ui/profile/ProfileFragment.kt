package com.example.explorethecity.ui.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.explorethecity.MainActivity
import com.example.explorethecity.databinding.FragmentProfileBinding
import com.example.explorethecity.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db:FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        db = Firebase.firestore

        getDataFromFirestore()

        auth = Firebase.auth
        binding.logoutButton.setOnClickListener(){
            auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    private fun getDataFromFirestore() {
        val docRef = db.collection("users")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data")
                    val documents = document.documents
                    for(document in documents){
                        if (document.get("id") == auth.currentUser?.uid){

                            val name = document.get("name") as String
                            val surname = document.get("surname") as String
                            val email = document.get("email") as String
                            val city = document.get("city") as String
                            val password = document.get("password") as String

                            fillContent(name, surname, email, city, password)
                        }
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

    }
    fun fillContent(name:String, surname:String, email:String, city:String, password:String){
        binding.nameTextView.text=name + " " + surname
        binding.mailTextView.text = email
        binding.cityTextView.text = city
        binding.passwordTextView.text = password
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}