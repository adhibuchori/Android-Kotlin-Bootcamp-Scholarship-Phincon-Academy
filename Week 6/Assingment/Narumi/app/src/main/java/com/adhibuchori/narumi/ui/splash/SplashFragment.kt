package com.adhibuchori.narumi.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentSplashBinding
import com.adhibuchori.narumi.utils.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding, ViewModel>(FragmentSplashBinding::inflate) {
    override fun setupViews() {
        setupSplash()
    }

    private fun setupSplash() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1500)

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build()

            val navController = view?.let { Navigation.findNavController(it) }

            navController?.navigate(
                R.id.action_splashFragment_to_onBoardingFragment,
                null,
                navOptions
            )
        }
    }
}