package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentLoginBinding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,container,false)
        return fragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentLoginBinding.etRegisterpage.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        fragmentLoginBinding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        fragmentLoginBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        fragmentLoginBinding.etloginButton.setOnClickListener {
            val email:String=fragmentLoginBinding.etLoginEmail.text.toString().trim { it<=' ' }
            val password:String=fragmentLoginBinding.etLoginPassword.text.toString().trim { it<=' ' }



            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){

                        findNavController().navigate(R.id.action_loginFragment_to_home)
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(this@LoginFragment.requireActivity(),exception.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }
        }
    }

}