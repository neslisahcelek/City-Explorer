package com.example.explorethecity.ui.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explorethecity.databinding.FragmentEventListBinding

class EventListFragment : Fragment() {

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!
    private val eventAdapter = EventAdapter(arrayListOf())
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eventListViewModel =
            ViewModelProvider(this).get(EventListViewModel::class.java)

        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerViewEventList
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,1,
            GridLayoutManager.VERTICAL, false)

        recyclerView.adapter = EventAdapter(arrayListOf())
        //observeLiveData()
        //adapter?.notifyDataSetChanged()
    }

    private fun observeLiveData() {
        /*
        viewModel.events.observe(viewLifecycleOwner, Observer { events ->
            events?.let {
                eventAdapter.updateEventList(events)
            }
        })


         */

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}