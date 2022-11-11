package com.schneider.dailywins.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.schneider.dailywins.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var homeViewModel: HomeFragmentViewModel

    private lateinit var binding: FragmentHomeBinding
    var adapter = DailyWinAdapter()


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

        homeViewModel.winList.observe(viewLifecycleOwner) { wins ->
             wins?.let {
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