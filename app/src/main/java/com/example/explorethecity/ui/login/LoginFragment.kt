package com.example.explorethecity.ui.login

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.explorethecity.MainActivity
import com.example.explorethecity.databinding.FragmentLoginBinding
import com.example.explorethecity.ui.signup.SignupFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            Log.d(ContentValues.TAG, "onclick")
            login()
        }

        binding.buttonSignUp.setOnClickListener{
            SignUpButtonClicked()
        }

        binding.loginWithGoogleButton.setOnClickListener{
            LoginWithGoogleButtonClicked()
        }
        val root: View = binding.root

        return root
    }

    private fun login() {
        val mail = binding.mailInputLogin.text.toString()
        val password = binding.passwordInputLogin.text.toString()

        if (mail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(mail, password)
                .addOnSuccessListener {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                }
        }
        else{
            Toast.makeText(getActivity(), "Please fill al fields", Toast.LENGTH_SHORT).show();
            Log.d("login", "please fill al fields")
        }
    }
    fun LoginWithGoogleButtonClicked() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }
    fun SignUpButtonClicked() {
        val fragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.explorethecity.R.id.container, SignupFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}