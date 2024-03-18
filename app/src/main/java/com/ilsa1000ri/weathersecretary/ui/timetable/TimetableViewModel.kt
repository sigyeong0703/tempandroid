package com.ilsa1000ri.weathersecretary.ui.timetable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimetableViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "시간표 페이지"
    }
    val text: LiveData<String> = _text
}