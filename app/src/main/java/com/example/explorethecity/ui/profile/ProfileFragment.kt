package com.example.explorethecity.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.explorethecity.MainActivity
import com.example.explorethecity.databinding.FragmentProfileBinding
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
    var pickedPicture: Uri? = null
    var pickedBitMap : Bitmap? = null

    private lateinit var sp: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        db = Firebase.firestore

        sp = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        fillContent()

        auth = Firebase.auth
        binding.logoutButton.setOnClickListener(){
            auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.profileImage.setOnClickListener(){
            Log.d("image","click")
            setProfilePicture()
        }


        return root
    }
    fun fillContent(){
        binding.nameTextView.text=sp.getString("name","").toString() + " " + sp.getString("surname","").toString()
        binding.mailTextView.text = sp.getString("email","").toString()
        binding.cityTextView.text = sp.getString("city","").toString()
        binding.passwordTextView.text = sp.getString("password","").toString()
    }

    private fun setProfilePicture() {
        if (requireActivity().let { ContextCompat.checkSelfPermission(it,android.Manifest.permission.READ_EXTERNAL_STORAGE) }
            != PackageManager.PERMISSION_GRANTED){
            Log.d("image","permission available")
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            Log.d("image","permission")
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,2)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galeriIntext = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntext,2)

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var imageView = binding.profileImage
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPicture = data.data
            if (pickedPicture != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(requireActivity().contentResolver,pickedPicture!!)
                    pickedBitMap = ImageDecoder.decodeBitmap(source)
                    imageView.setImageBitmap(pickedBitMap)
                    Log.d("image","done")
                }
                else {
                    pickedBitMap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,pickedPicture)
                    imageView.setImageBitmap(pickedBitMap)
                    Log.d("image","done")
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}