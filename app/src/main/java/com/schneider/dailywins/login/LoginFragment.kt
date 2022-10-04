package com.schneider.dailywins.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.R
import com.schneider.dailywins.databinding.FragmentLoginBinding
import com.schneider.dailywins.network.Resource
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModel: LoginFragmentViewModel
//      var viewModel: LoginFragmentViewModel = LoginFragmentViewModel()

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        auth = Firebase.auth
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
                val user = auth.currentUser
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