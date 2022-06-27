package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.data.viewmodels.FavoritesViewModel
import com.sumeyyeemre.ecommerceapp.databinding.FragmentFavoriteBinding
import com.sumeyyeemre.ecommerceapp.presentation.FavoritesAdapter

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    private val adapterfavorites by lazy { FavoritesAdapter() }

    private val viewModel by lazy { FavoritesViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=DataBindingUtil.inflate(inflater, R.layout.fragment_favorite,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            with(viewModel){
                productsFavoriteList.observe(viewLifecycleOwner){list->
                    recyclerViewFavorites.apply {
                        setHasFixedSize(true)
                        adapter=adapterfavorites.also { adapter->
                            adapter.updateList(list)
                            Log.v("favoritefragmentt","$list")
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