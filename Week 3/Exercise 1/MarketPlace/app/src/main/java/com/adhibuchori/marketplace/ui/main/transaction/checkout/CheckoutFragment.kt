package com.adhibuchori.marketplace.ui.main.transaction.checkout

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentCheckoutBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleAndIconToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class CheckoutFragment :
    BaseFragment<FragmentCheckoutBinding, ViewModel>(FragmentCheckoutBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
    }

    private fun adjustToolbar() {
        val titleAndIconToolbarManager = TitleAndIconToolbarManager()
        val title = requireContext().getString(R.string.checkout)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        context?.let {
            setupToolbar(
                binding.checkoutPageToolbar,
                it,
                title,
                drawable,
                titleAndIconToolbarManager
            )
        }
    }

    private fun setupNavigation() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graphs, true)
            .build()
        binding.checkoutPageToolbar.ivToolbarProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_checkoutFragment_to_cartFragment,
                null,
                navOptions
            )
        }
    }
}