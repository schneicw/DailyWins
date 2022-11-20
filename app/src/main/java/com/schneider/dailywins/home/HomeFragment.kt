package com.schneider.dailywins.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationBarView
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

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.highlights_menu_item -> {
                    homeViewModel.getAllUserHighlights()
                }
                R.id.today_menu_item -> {
                    homeViewModel.getAllUserWins()
                }
            }
            true
        }

        homeViewModel.winList.observe(viewLifecycleOwner) { wins ->
             wins?.let {
                 adapter.refreshData()
                 adapter.setData(it)
            }
        }
        homeViewModel.getAllUserWins()
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