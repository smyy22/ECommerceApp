package com.sumeyyeemre.ecommerceapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.sumeyyeemre.ecommerceapp.R
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesRoomModel
import com.sumeyyeemre.ecommerceapp.data.viewmodels.FavoritesViewModel
import com.sumeyyeemre.ecommerceapp.data.viewmodels.ProductViewModel
import com.sumeyyeemre.ecommerceapp.databinding.FragmentCardDetailBinding

class CardDetailFragment : Fragment() {
    private lateinit var fragmentCardDetailBinding: FragmentCardDetailBinding
    private val args: CardDetailFragmentArgs by navArgs()

    private val viewModel by lazy { ProductViewModel(requireContext()) }

    private val favoritesViewModel by lazy { FavoritesViewModel(requireContext()) }

    var onAddFavoritesClick: (ProductsFavoritesRoomModel) -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCardDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_card_detail, container, false)
        return fragmentCardDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.productModel

        fragmentCardDetailBinding.apply {
            productModel = product
            Picasso.get().load(product.image).into(imageView2)

            addToCartButton.setOnClickListener {
                FirebaseAuth.getInstance().currentUser?.let { user ->
                    viewModel.addToBag(
                        user.uid,
                        product.title,
                        product.price,
                        product.description,
                        product.category,
                        product.image,
                        product.rate,
                        product.count,
                        product.sale_state
                    )
                }

            }

            fabtoFavorite.setOnClickListener {

                favoritesViewModel.addProducttoFavorite(
                    ProductsFavoritesRoomModel(
                        category = product.category,
                        count = product.count,
                        description = product.description,
                        image = product.image,
                        price = product.price,
                        rate = product.rate,
                        sale_state = product.sale_state,
                        title = product.title,
                        user = product.user
                    )
                )
            }
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_cardDetailFragment_to_home)
            }
        }
    }

}