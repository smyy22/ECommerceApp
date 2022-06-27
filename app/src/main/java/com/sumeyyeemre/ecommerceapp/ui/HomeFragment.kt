package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.data.viewmodels.ProductViewModel
import com.sumeyyeemre.ecommerceapp.databinding.FragmentHomeBinding
import com.sumeyyeemre.ecommerceapp.presentation.ProductAdapter


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ProductViewModel(requireContext()) }
    private val productAdapter by lazy { ProductAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        binding.recyclerView.apply {

        }

    }

    private fun initObservers(){
        with(binding){
            with(viewModel){
                productsList.observe(viewLifecycleOwner){list->
                    recyclerView.apply {
                        setHasFixedSize(true)
                        adapter=productAdapter.also {adapter->
                            adapter.updateList(list.takeLast(10).reversed())
                            //Log.v("deneme5","$list")

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