package com.schneider.dailywins.home

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.Navigation
import com.schneider.dailywins.R
import com.schneider.dailywins.data.DailyWin
import com.schneider.dailywins.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var homeViewModel: HomeFragmentViewModel

    private lateinit var binding: FragmentHomeBinding
    private var adapter = DailyWinAdapter(itemClickCallback = fun(win: DailyWin) {
        homeViewModel.editWin(win)
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        binding.addWins.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addWinFragment)
        }

        binding.settings.setOnClickListener {
            showPopup(it)
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.highlights_menu_item -> {
                    homeViewModel.getAllUserHighlights()
                }
                R.id.today_menu_item -> {
                    homeViewModel.getAllUserWins()
                }
                R.id.calendar_menu_item -> {
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_calendarFragment)
                }
            }
            true
        }

        // onCreateOptionsMenu

        homeViewModel.winList.observe(viewLifecycleOwner) { wins ->
             wins?.let {
                 adapter.refreshData()
                 adapter.setData(it)
            }
        }
        homeViewModel.getAllUserWins()
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(requireActivity(), v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.actions, popup.menu)
        popup.show()

        popup.setOnMenuItemClickListener {
            if (it.itemId == R.id.logout) {
                homeViewModel.logout()
            }
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_loginFragment)
            true
        }
        // set on item click listener
    }
}


// wins
    // user id
    // user id
        // dailywins
            // random id
                // date
                // win list
                // photo
    // user id