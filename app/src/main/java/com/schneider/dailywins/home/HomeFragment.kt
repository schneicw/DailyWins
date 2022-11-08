package com.schneider.dailywins.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.schneider.dailywins.R
import com.schneider.dailywins.databinding.FragmentLoginBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var homeViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        val wins = homeViewModel.getAllUserWins()

//        homeViewModel.flowersLiveData.observe(this, {
//            it?.let {
//                flowersAdapter.submitList(it as MutableList<Flower>)
//                headerAdapter.updateFlowerCount(it.size)
//            }
//        })

//        val winsAdapter = DailyWinAdapter { flower -> adapterOnClick(flower) }
//        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        return inflater.inflate(R.layout.fragment_home, container, false)
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