package com.example.classpacket.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.classpacket.R
import com.example.classpacket.database.RegisterDatabase
import com.example.classpacket.database.RegisterRepository
import com.example.classpacket.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this

        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","inside observe")
                displayUsersList()
                loginViewModel.doneNavigatingRegiter()
            }
        })

        loginViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Snackbar.make(binding!!.root,"Please fill all fields",Snackbar.LENGTH_LONG).show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.errotoastUsername .observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Snackbar.make(binding!!.root,"User doesn't exist, please Register.",Snackbar.LENGTH_LONG).show()
                loginViewModel.donetoastErrorUsername()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Snackbar.make(binding!!.root,"Please check your Password.",Snackbar.LENGTH_LONG).show()
                loginViewModel.donetoastInvalidPassword()
            }
        })

        loginViewModel.navigatetoUserDetails.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","inside observe")
                navigateUserDetails()
                loginViewModel.doneNavigatingUserDetails()
            }
        })


        return binding.root
    }


    private fun displayUsersList() {
        Log.i("MYTAG","insidedisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateUserDetails() {
        Log.i("MYTAG","insidedisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
        NavHostFragment.findNavController(this).navigate(action)
    }
}
