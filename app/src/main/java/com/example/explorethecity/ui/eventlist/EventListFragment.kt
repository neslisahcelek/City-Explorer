package com.example.explorethecity.ui.eventlist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.explorethecity.databinding.FragmentEventListBinding

class EventListFragment : Fragment() {

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventListViewModel by viewModels()
    private lateinit var sp: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerViewEventList.adapter = EventAdapter()

        sp = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        if (sp.getString("city","").toString() == ""){
            sp.edit().putString("city", "Antalya").apply()
        }
        binding.cityTextView.text = "Let's Discover " + sp.getString("city","").toString() + "!"

        viewModel.setSharedPreferences(requireContext())

        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}