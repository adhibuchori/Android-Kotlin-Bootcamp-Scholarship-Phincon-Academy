package com.adhibuchori.marketplace.ui.onboarding

import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentOnBoardingBinding
import com.adhibuchori.marketplace.ui.onboarding.adapter.IntroSliderAdapter
import com.adhibuchori.marketplace.utils.BaseFragment

class OnBoardingFragment :
    BaseFragment<FragmentOnBoardingBinding, ViewModel>(FragmentOnBoardingBinding::inflate) {

    private val introSlides = IntroSliderAdapter(
        listOf(
            IntroSlide(
                R.drawable.iv_first_slider_content
            ),
            IntroSlide(
                R.drawable.iv_second_slider_content
            ),
            IntroSlide(
                R.drawable.iv_third_slider_content
            )
        )
    )

    override fun setupViews() {
        setUpNavigation()
        setUpIndicators()
        setCurrentIndicator(0)
        setUpOnBoardingSlider()
    }

    private fun setUpOnBoardingSlider() {
        with(binding) {
            vpOnBoardingPageSlider.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })

            vpOnBoardingPageSlider.adapter = introSlides
        }
    }

    private fun setUpNavigation() {

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graphs, true)
            .build()

        with(binding) {
            btnOnBoardingPageJoinNow.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_onBoardingFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }

            tvOnBoardingSkip.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_onBoardingFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }

            tvOnBoardingNext.setOnClickListener {
                if (vpOnBoardingPageSlider.currentItem + 1 < introSlides.itemCount) {
                    vpOnBoardingPageSlider.currentItem += 1
                } else {
                    Navigation.findNavController(it).navigate(
                        R.id.action_onBoardingFragment_to_loginFragment,
                        null,
                        navOptions
                    )
                }
            }
        }
    }

    private fun setUpIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSlides.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(16, 0, 16, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.vOnBoardingIndicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.vOnBoardingIndicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.vOnBoardingIndicatorsContainer.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}