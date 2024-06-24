package com.adhibuchori.marketplace.ui.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAnime()
    }

    private fun setUpAnime() {
        with(binding) {
            animateView(ivSplashPageAppLogo, SCALE_X, 0f, 1f, 700L)
            animateView(ivSplashPageAppLogo, SCALE_Y, 0f, 1f, 700L)

            animateView(ivSplashPageLogoBackgroundGreen, SCALE_X, 0f, 1f, 700L)
            animateView(ivSplashPageLogoBackgroundGreen, SCALE_Y, 0f, 1f, 700L)
            animateView(ivSplashPageLogoBackgroundGreen, TRANSLATION_Y, 0f, -145f, 1200L)

            animateView(ivSplashPageLogoBackgroundRed, SCALE_X, 0f, 1f, 700L)
            animateView(ivSplashPageLogoBackgroundRed, SCALE_Y, 0f, 1f, 700L)
            animateView(ivSplashPageLogoBackgroundRed, TRANSLATION_Y, 0f, -60f, 1000L)
            animateView(ivSplashPageLogoBackgroundRed, TRANSLATION_X, 0f, 80f, 1000L)
            animateView(ivSplashPageLogoBackgroundRed, ROTATE_ANIMATION, 0f, 20f, 1200L)

            animateView(ivSplashPageLogoBackgroundYellow, SCALE_X, 0f, 1f, 700L)
            animateView(ivSplashPageLogoBackgroundYellow, SCALE_Y, 0f, 1f, 700L)
            animateView(ivSplashPageLogoBackgroundYellow, TRANSLATION_Y, 0f, -80f, 1000L)
            animateView(ivSplashPageLogoBackgroundYellow, TRANSLATION_X, 0f, -65f, 1000L)
            animateView(ivSplashPageLogoBackgroundYellow, ROTATE_ANIMATION, 0f, -20f, 1200L)
        }

        Handler(requireActivity().mainLooper).postDelayed({

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graphs, true)
                .build()

            val navController = view?.let { Navigation.findNavController(it) }

            navController?.navigate(
                R.id.action_splashScreenFragment_to_onBoardingFragment,
                null,
                navOptions
            )
        }, 1500)
    }

    private fun animateView(
        view: View,
        propertyName: String,
        startVal: Float,
        endVal: Float,
        duration: Long = 1500
    ) {
        view.run {
            when (propertyName) {
                TRANSLATION_X -> {
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_X, startVal, endVal).apply {
                        this.duration = duration
                    }.start()
                }

                TRANSLATION_Y -> {
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startVal, endVal).apply {
                        this.duration = duration
                    }.start()
                }

                SCALE_X -> {
                    ObjectAnimator.ofFloat(view, View.SCALE_X, startVal, endVal).apply {
                        this.duration = duration
                    }.start()
                }

                SCALE_Y -> {
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, startVal, endVal).apply {
                        this.duration = duration
                    }.start()
                }

                ROTATE_ANIMATION -> {
                    ObjectAnimator.ofFloat(view, View.ROTATION, startVal, endVal).apply {
                        this.duration = duration
                    }.start()
                }

                ALPHA_ANIMATION -> {
                    ObjectAnimator.ofFloat(view, View.ALPHA, startVal, endVal).apply {
                        this.duration = duration
                    }.start()
                }

                else -> {
                    val marginParams = view.layoutParams as LinearLayout.LayoutParams
                    marginParams.setMargins(0, 0, 0, 60)
                }
            }
        }
    }

    companion object {
        const val TRANSLATION_X = "translationX"
        const val TRANSLATION_Y = "translationY"
        const val SCALE_X = "scaleX"
        const val SCALE_Y = "scaleY"
        const val ROTATE_ANIMATION = "rotation"
        const val ALPHA_ANIMATION = "alpha"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}