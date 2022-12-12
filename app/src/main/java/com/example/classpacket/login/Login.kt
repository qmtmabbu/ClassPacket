package com.example.classpacket.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.classpacket.database.UserDatabase
import com.example.classpacket.R
import com.example.classpacket.databinding.FragmentLoginBinding


class Login : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        val loginViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(LoginViewModel::class.java)

        binding.btnReg.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_register)
        }

        loginViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            this.findNavController().navigate(R.id.action_login_to_homeFragment)
            loginViewModel.doneNavigating()
        })

        binding.loginViewModel = loginViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }
}