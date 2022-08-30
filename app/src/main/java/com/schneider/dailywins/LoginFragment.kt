package com.schneider.dailywins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.schneider.dailywins.R
import com.schneider.dailywins.databinding.FragmentLoginBinding
import com.schneider.dailywins.network.AuthApi
import com.schneider.dailywins.network.RemoteDataSource
import com.schneider.dailywins.repository.AuthRepository
import javax.inject.Inject

private val remoteDataSource = RemoteDataSource()
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModel: AuthActivityViewModel
    // inject view model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            viewModel.login(email, password)
        }
        val view = binding.root
        return view
    }

}