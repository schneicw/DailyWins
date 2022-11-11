package com.schneider.dailywins.addwins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.schneider.dailywins.R
import com.schneider.dailywins.databinding.FragmentAddBinding
import com.schneider.dailywins.databinding.FragmentHomeBinding
import com.schneider.dailywins.home.HomeFragmentViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddWinFragment : DaggerFragment() {

    @Inject
    lateinit var addWinViewModel: AddWinFragmentViewModel

    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("AddWinFragment onViewCreated")
        binding.submitWins.setOnClickListener {
            println("ADDWINFRAGMEBT click")
            val win1 = binding.winText1.text.toString()
            val win2 = binding.winText2.text.toString()
            val win3 = binding.winText3.text.toString()
            val wins = listOf(win1, win2, win3)
            addWinViewModel.addWin(wins)
            Navigation.findNavController(view).navigate(R.id.action_addWinFragment_to_homeFragment)
        }
    }
}