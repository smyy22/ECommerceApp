package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.data.viewmodels.CategoryViewModel
import com.sumeyyeemre.ecommerceapp.databinding.FragmentShopBinding
import com.sumeyyeemre.ecommerceapp.presentation.CategoryAdapter


class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val adapterCategory by lazy { CategoryAdapter() }

    private val viewModel by lazy { CategoryViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=DataBindingUtil.inflate(inflater, R.layout.fragment_shop,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            with(viewModel){
                categoryList.observe(viewLifecycleOwner){list->
                    recyclerCategory.apply {
                        setHasFixedSize(true)
                        adapter=adapterCategory.also { adapter->
                            adapter.updateList(list)
                        }
                    }
                    //Log.v("shopfragment","$list")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}