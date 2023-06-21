package com.example.explorethecity.ui.signup

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.explorethecity.MainActivity
import com.example.explorethecity.R
import com.example.explorethecity.databinding.FragmentLoginBinding
import com.example.explorethecity.databinding.FragmentSignupBinding
import com.example.explorethecity.model.User
import com.example.explorethecity.repository.FirestoreClass
import com.example.explorethecity.ui.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var intent : Intent
    //private lateinit var database : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        binding.signupButton.setOnClickListener {
            Log.d(ContentValues.TAG, "onclick")
            val name = binding.nameInputSignup.text.toString()
            val surname = binding.surnameInputSignup.text.toString()
            val email = binding.mailInputSignup.text.toString()
            val password = binding.passwordInputSignup.text.toString()
            val city = binding.cityInputSignup.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                signup(name, surname, email, password, city)
            }
            else{
                Toast.makeText(activity, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonLogin.setOnClickListener{
            LoginButtonClicked()
        }

        val root: View = binding.root
        return root    }

    private fun signup(name: String, surname: String, email: String, password: String, city: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val firebaseUser = auth.currentUser
                    val user = User(firebaseUser!!.uid, name, surname, email, password, city)
                    FirestoreClass().registerUser(this, user)

                } else {
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), task.exception.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun userRegisteredSuccess() {
        Toast.makeText(
            activity,
            "You have successfully registered!",
            Toast.LENGTH_SHORT
        ).show()
        performIntent()
    }
    private fun performIntent() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }


    fun LoginButtonClicked() {
        Log.d(ContentValues.TAG, "LoginButtonClicked")
        val fragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.explorethecity.R.id.container, LoginFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}