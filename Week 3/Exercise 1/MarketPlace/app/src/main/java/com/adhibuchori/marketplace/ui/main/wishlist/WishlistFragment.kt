package com.adhibuchori.marketplace.ui.main.wishlist

import androidx.lifecycle.ViewModel
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentWishlistBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleOnlyToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class WishlistFragment : BaseFragment<FragmentWishlistBinding, ViewModel>(FragmentWishlistBinding::inflate) {

    override fun setupViews() {
        adjustToolbar()
        setupNavigation()
    }

    private fun adjustToolbar() {
        val titleOnlyToolbarManager = TitleOnlyToolbarManager()
        val title = requireContext().getString(R.string.user_name)
        context?.let {
            setupToolbar(
                binding.wishlistPageToolbar,
                it,
                title,
                null,
                titleOnlyToolbarManager
            )
        }
    }

    private fun setupNavigation() {
        with(binding.wishlistPageToolbar) {
            ivToolbarNotification.setOnClickListener(
                androidx.navigation.Navigation.createNavigateOnClickListener(com.adhibuchori.marketplace.R.id.action_bottomNavigationContainerFragment_to_notificationFragment)
            )
            ivToolbarShoppingCart.setOnClickListener(
                androidx.navigation.Navigation.createNavigateOnClickListener(com.adhibuchori.marketplace.R.id.action_bottomNavigationContainerFragment_to_cartFragment)
            )
        }
    }
}