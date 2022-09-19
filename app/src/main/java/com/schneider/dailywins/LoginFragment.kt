package com.schneider.dailywins

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.databinding.FragmentLoginBinding
import com.schneider.dailywins.network.RemoteDataSource
import com.schneider.dailywins.network.Resource


private val remoteDataSource = RemoteDataSource()

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    var viewModel: AuthActivityViewModel = AuthActivityViewModel()
    // inject view model

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    println(it.errorBody)
                    println(it.errorCode)
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            easyLogin(email, password, view)
//            viewModel.login(email, password)
        }
        return view
    }

    fun easyLogin(email: String, password: String, view: View) {
        binding.progressBar2.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.progressBar2.visibility = View.GONE
                if (task.isSuccessful) {
                    Log.d("Tag", "loginUserWithEmail:success")
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Tag", "loginUserWithEmail:failure", task.exception)
                    Toast.makeText(requireContext(), "Failed login", Toast.LENGTH_LONG).show()
//                    updateUI(null)
                }
            }
    }
}