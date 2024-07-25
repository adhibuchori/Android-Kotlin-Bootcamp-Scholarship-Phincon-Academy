package com.adhibuchori.narumi.ui.main

import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.adhibuchori.narumi.databinding.FragmentMainBinding
import com.adhibuchori.narumi.utils.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, ViewModel>(FragmentMainBinding::inflate) {
    override fun setupViews() {
        setupBottomNavigation()
        setupStatusBar()
    }

    private fun setupBottomNavigation() {
        val navController =
            childFragmentManager.findFragmentById(binding.fcvMainPageChildContainer.id)
                ?.findNavController()
        val bottomNavigationView = binding.bnvMainPageNavigation

        navController?.let {
            bottomNavigationView.setupWithNavController(it)
        }
    }

    private fun setupStatusBar() {
        activity?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(
                it,
                true
            )
        }
    }
}