package com.example.explorethecity.ui.eventlist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.explorethecity.model.Event
import com.example.explorethecity.model.photo
import com.example.explorethecity.network.eventAPI
import kotlinx.coroutines.launch

class EventListViewModel : ViewModel() {
    private val eventApi = eventAPI.retrofitService

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    init {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        viewModelScope.launch {
            try {
                val response = eventApi.getEvents()
                _events.value=response
                Log.d("api", response.toString())

            } catch (e: Exception) {

                e.message?.let { Log.d("api error", it) }
            }
            }
        }

}