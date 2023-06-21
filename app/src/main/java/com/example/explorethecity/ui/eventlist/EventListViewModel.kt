package com.example.explorethecity.ui.eventlist

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.explorethecity.model.Event
import com.example.explorethecity.model.EventsResponse
import com.example.explorethecity.network.eventAPI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class EventListViewModel : ViewModel() {
    private val eventApi = eventAPI.retrofitService
    val apiKey =   "b1a9bcd3fc54906cf7655a35b126cc9108fa1a9c61580c7f07c7021862968b4a"
    private var auth: FirebaseAuth
    private var db: FirebaseFirestore
    private var city = "Antalya"

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private val _eventResponse = MutableLiveData<EventsResponse>()
    val eventResponse: LiveData<EventsResponse>
        get() = _eventResponse

    init {
        db = Firebase.firestore
        auth = Firebase.auth
        getCityFromFirestore()
    }

    private fun getCityFromFirestore() {
        val docRef = db.collection("users")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data")
                    val documents = document.documents
                    for(document in documents){
                        if (document.get("id") == auth.currentUser?.uid){
                            city = document.get("city") as String
                            Log.d(ContentValues.TAG, "get city $city")
                            getDataFromAPI()
                        }
                    }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

    private fun getDataFromAPI() {
        viewModelScope.launch {
            try {
                Log.d(ContentValues.TAG, "try city $city")
                val response = eventApi.getEventsByCity("Events in $city", "en", "us", apiKey)
                _eventResponse.value=response
                _events.value = response.events_results

                Log.d("api", response.toString())

            } catch (e: Exception) {

                e.message?.let { Log.d("api error", it) }
            }
            }
        }

}