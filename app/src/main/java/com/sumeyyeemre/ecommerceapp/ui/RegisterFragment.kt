package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var fragmentRegisterBinding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentRegisterBinding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_register,container,false)
        return fragmentRegisterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentRegisterBinding.etLoginpage.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        fragmentRegisterBinding.etRegisterbButton.setOnClickListener {
            when {
                TextUtils.isEmpty(fragmentRegisterBinding.etRegisterName.text.toString().trim { it<=' ' })-> {
                    Toast.makeText(
                        this@RegisterFragment.requireActivity(),
                        "Please enter name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(fragmentRegisterBinding.etRegisterEmail.text.toString().trim { it<=' ' })-> {
                    Toast.makeText(
                        this@RegisterFragment.requireActivity(),
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(fragmentRegisterBinding.etRegisterPassword.text.toString().trim { it<=' ' })-> {
                    Toast.makeText(
                        this@RegisterFragment.requireActivity(),
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else ->{
                    val name:String=fragmentRegisterBinding.etRegisterName.text.toString().trim { it<=' ' }
                    val email:String=fragmentRegisterBinding.etRegisterEmail.text.toString().trim { it<=' ' }
                    val password:String=fragmentRegisterBinding.etRegisterPassword.text.toString().trim { it<=' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener{ task ->
                            if(task.isSuccessful){
                                val firebaseUser:FirebaseUser=task.result!!.user!!
                                Toast.makeText(
                                    this@RegisterFragment.requireActivity(),
                                    "You were registered successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }
                        }.addOnFailureListener { exception ->
                            Toast.makeText(this@RegisterFragment.requireActivity(),exception.localizedMessage,Toast.LENGTH_LONG).show()
                        }


                }
            }
        }
    }

}