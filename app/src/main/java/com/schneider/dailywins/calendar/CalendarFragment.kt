package com.schneider.dailywins.calendar

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.schneider.dailywins.R
import com.schneider.dailywins.databinding.FragmentCalendarBinding
import com.schneider.dailywins.utils.Decorator
import dagger.android.support.DaggerFragment
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject


class CalendarFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CalendarViewModel

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val activity = activity as Activity
        viewModel.getAllUserWins()


        viewModel.winList.observe(viewLifecycleOwner) { wins ->
            wins?.forEach() {
                val cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"))
                cal.time = it.date
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH) + 1 // see why off by 1
                val day = cal.get(Calendar.DAY_OF_MONTH)
                binding.calendarView.addDecorator(Decorator(activity, CalendarDay.from(year, month, day)))
                binding.calendarView.setDateSelected(CalendarDay.from(year, month, day), true)
            }
        }

        binding.calendarView.selectionMode

        binding.bottomNavigationView.selectedItemId = R.id.calendar_menu_item
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.highlights_menu_item -> {
                    Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_homeFragment)
                }
                R.id.today_menu_item -> {
                    Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_homeFragment)
                }
                R.id.calendar_menu_item -> {
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_calendarFragment)
                }
            }
            true
        }
    }
}