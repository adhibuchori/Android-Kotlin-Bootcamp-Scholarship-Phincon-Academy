package com.adhibuchori.marketplace.ui.main.productDetail

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentProductDetailBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleAndIconToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class ProductDetailFragment :
    BaseFragment<FragmentProductDetailBinding, ViewModel>(FragmentProductDetailBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
        receiveStoreData()
    }

    private fun adjustToolbar() {
        val titleAndIconToolbarManager = TitleAndIconToolbarManager()
        val title = requireContext().getString(R.string.product_detail)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        context?.let {
            setupToolbar(
                binding.productDetailPageToolbar,
                it,
                title,
                drawable,
                titleAndIconToolbarManager
            )
        }
    }

    private fun setupNavigation() {
        with(binding) {
            productDetailPageToolbar.ivToolbarProfile.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            btnProductDetailPageBuyNow.setOnClickListener {
                val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCheckoutFragment()
                findNavController().navigate(action)
            }
            btnProductDetailPageAddCart.setOnClickListener {
                Toast.makeText(requireContext(), "Produk ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun receiveStoreData() {
        val args: ProductDetailFragmentArgs by navArgs()
        val storeData = args.storeData

        with(binding) {
            tvProductDetailPageProductName.text = storeData.productName
            tvProductDetailPageProductPrice.text = storeData.productPrice

//            Glide.with(ivProductImage.context)
//                .load(storeData.productImageUrl)
//                .into(binding.ivProductImage)
        }
    }
}