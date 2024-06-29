package com.adhibuchori.marketplace.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentHomeBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleOnlyToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class HomeFragment : BaseFragment<FragmentHomeBinding, ViewModel>(FragmentHomeBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        val titleOnlyManager = TitleOnlyToolbarManager()
        val title = requireContext().getString(R.string.user_name)
        context?.let {
            setupToolbar(
                binding.homePageToolbar,
                it,
                title,
                null,
                titleOnlyManager
            )
        }
    }

    private fun setupNavigation() {
        with(binding) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graphs, true)
                .build()

            btnHomePageLogout.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_bottomNavigationContainerFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }
            with(homePageToolbar) {
                ivToolbarNotification.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_bottomNavigationContainerFragment_to_notificationFragment)
                )
                ivToolbarShoppingCart.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_bottomNavigationContainerFragment_to_cartFragment)
                )
            }
        }
    }
}