package com.example.explorethecity.ui.placelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaceListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is placelist Fragment"
    }
    val text: LiveData<String> = _text
}