package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {
    private lateinit var fragmentForgotPasswordBinding: FragmentForgotPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentForgotPasswordBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)
        return fragmentForgotPasswordBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(fragmentForgotPasswordBinding) {
            sendButton.setOnClickListener {
                val email:String = fragmentForgotPasswordBinding.etEmail.text.toString().trim { it<=' ' }
                //Log.v("sifredeğistir", "$email")
                if (email.isEmpty()) {
                    Toast.makeText(
                        this@ForgotPasswordFragment.requireActivity(),
                        "Please enter email address.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@ForgotPasswordFragment.requireActivity(),
                                    "Email sent successfully to reset your password!",
                                    Toast.LENGTH_LONG
                                ).show()
                                findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
                            } else {
                                Toast.makeText(
                                    this@ForgotPasswordFragment.requireActivity(),
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            }
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
            }
        }
    }
}