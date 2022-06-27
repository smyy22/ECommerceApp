package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.data.viewmodels.BagViewModel
import com.sumeyyeemre.ecommerceapp.databinding.FragmentBagBinding
import com.sumeyyeemre.ecommerceapp.presentation.BagAdapter

class BagFragment : Fragment() {
    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BagViewModel(requireContext()) }
    private val bagAdapter by lazy { BagAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bag, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBag()
    }

    private fun initBag() {
        with(binding) {
            with(viewModel) {

                FirebaseAuth.getInstance().currentUser?.let {
                    getBagProducts(it.uid)
                }

                bagList.observe(viewLifecycleOwner) { list ->
                    recyclerViewBag.apply {
                        setHasFixedSize(true)
                        adapter = bagAdapter.also { adapter ->
                            //println(list)
                            adapter.bagupdateList(list)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}