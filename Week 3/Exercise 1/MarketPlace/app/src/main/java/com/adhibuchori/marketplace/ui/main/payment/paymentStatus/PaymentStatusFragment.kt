package com.adhibuchori.marketplace.ui.main.payment.paymentStatus

import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentPaymentStatusBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.ToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class PaymentStatusFragment :
    BaseFragment<FragmentPaymentStatusBinding, ViewModel>(FragmentPaymentStatusBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
    }

    private fun adjustToolbar() {
        val toolbarManager = ToolbarManager()
        val title = requireContext().getString(R.string.payment_status)
        context?.let {
            setupToolbar(
                binding.paymentStatusPageToolbar,
                it,
                title,
                null,
                toolbarManager
            )
        }
    }

    private fun setupNavigation() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graphs, true)
            .build()
        binding.paymentStatusPageToolbar.ivToolbarProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_paymentStatusFragment_to_bottomNavigationContainerFragment,
                null,
                navOptions
            )
        }
    }
}