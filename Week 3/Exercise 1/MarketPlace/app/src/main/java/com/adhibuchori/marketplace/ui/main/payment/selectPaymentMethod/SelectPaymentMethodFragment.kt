package com.adhibuchori.marketplace.ui.main.payment.selectPaymentMethod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentSelectPaymentMethodBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleAndIconToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class SelectPaymentMethodFragment :
    BaseFragment<FragmentSelectPaymentMethodBinding, ViewModel>(FragmentSelectPaymentMethodBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
    }

    private fun adjustToolbar() {
        val titleAndIconToolbarManager = TitleAndIconToolbarManager()
        val title = requireContext().getString(R.string.select_payment_method)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        context?.let {
            setupToolbar(
                binding.selectPaymentMethodPageToolbar,
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
        binding.selectPaymentMethodPageToolbar.ivToolbarProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_selectPaymentMethodFragment_to_checkoutFragment,
                null,
                navOptions
            )
        }
    }
}