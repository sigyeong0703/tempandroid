package com.ilsa1000ri.weathersecretary.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ilsa1000ri.weathersecretary.R
import com.ilsa1000ri.weathersecretary.databinding.FragmentCalendarBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView // RecyclerView 변수 이름을 변경

    private lateinit var selectedDate: LocalDate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        monthYearText = binding.monthYearText
        calendarRecyclerView = binding.RecyclerView // RecyclerView 변수 변경

        val prevBtn = binding.preBtn
        val nextBtn = binding.nextBtn

        selectedDate = LocalDate.now()

        setMonthView()

        prevBtn.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }

        nextBtn.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }

        // RecyclerView 객체 초기화
        calendarRecyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
        val adapter = CalendarAdapter(ArrayList())
        calendarRecyclerView.adapter = adapter

        return root
    }

    private fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")
        return date.format(formatter)
    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val dayList = daysInMonthArray(selectedDate)
        val adapter = CalendarAdapter(dayList)
        calendarRecyclerView.adapter = adapter
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val dayList = ArrayList<String>()

        val yearMonth = YearMonth.from(date)

        val lastDay = yearMonth.lengthOfMonth()

        val firstDay = selectedDate.withDayOfMonth(1)

        val dayOfWeek = firstDay.dayOfWeek.value

        for (i in 1 until 42) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add("")
            } else {
                dayList.add((i - dayOfWeek).toString())
            }
        }
        return dayList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
