package com.ilsa1000ri.weathersecretary.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "캘린더 페이지"
    }

    val text: LiveData<String> = _text
}