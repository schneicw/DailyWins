package com.schneider.dailywins.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.schneider.dailywins.R
import com.schneider.dailywins.dagger.FragmentScope
import com.schneider.dailywins.databinding.FragmentLoginBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@FragmentScope
class LoginFragment : DaggerFragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar2.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) { authResult ->
            authResult?.let {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
            } ?: Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
        }

        binding.register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            viewModel.loginWithFirebaseSDK(email, password)
        }
        return view
    }
}