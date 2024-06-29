package com.adhibuchori.marketplace.ui.main.transaction.transaction

import androidx.lifecycle.ViewModel
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentTransactionBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleOnlyToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class TransactionFragment :
    BaseFragment<FragmentTransactionBinding, ViewModel>(FragmentTransactionBinding::inflate) {

    override fun setupViews() {
        adjustToolbar()
        setupNavigation()
    }

    private fun adjustToolbar() {
        val titleOnlyToolbarManager = TitleOnlyToolbarManager()
        val title = requireContext().getString(R.string.user_name)
        context?.let {
            setupToolbar(
                binding.transactionPageToolbar,
                it,
                title,
                null,
                titleOnlyToolbarManager
            )
        }
    }

    private fun setupNavigation() {
        with(binding.transactionPageToolbar) {
            ivToolbarNotification.setOnClickListener(
                androidx.navigation.Navigation.createNavigateOnClickListener(com.adhibuchori.marketplace.R.id.action_bottomNavigationContainerFragment_to_notificationFragment)
            )
            ivToolbarShoppingCart.setOnClickListener(
                androidx.navigation.Navigation.createNavigateOnClickListener(com.adhibuchori.marketplace.R.id.action_bottomNavigationContainerFragment_to_cartFragment)
            )
        }
    }
}