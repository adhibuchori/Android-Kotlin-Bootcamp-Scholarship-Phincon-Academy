package com.adhibuchori.marketplace.ui.main.notification

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentNotificationBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleAndIconToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class NotificationFragment :
    BaseFragment<FragmentNotificationBinding, ViewModel>(FragmentNotificationBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
    }

    private fun setupNavigation() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graphs, true)
            .build()

        binding.notificationPageToolbar.ivToolbarProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_notificationFragment_to_bottomNavigationContainerFragment,
                null,
                navOptions
            )
        }
    }

    private fun adjustToolbar() {
        val titleAndIconToolbarManager = TitleAndIconToolbarManager()
        val title = requireContext().getString(R.string.notification_description)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        context?.let {
            setupToolbar(
                binding.notificationPageToolbar,
                it,
                title,
                drawable,
                titleAndIconToolbarManager
            )
        }
    }
}