package com.adhibuchori.marketplace.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.plusAssign
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpNavigation()
        setUpIndicators()
        setCurrentIndicator(0)

        binding.vpSliderOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        binding.vpSliderOnboarding.adapter = introSlides
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
            binding.vIndicatorsContainer.addView(indicators[i])
        }
    }

    private fun setUpNavigation() {

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graphs, true)
            .build()

        with(binding) {
            btnJoinNow.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_onBoardingFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }

            tvSkip.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_onBoardingFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }

            tvNext.setOnClickListener {
                if (vpSliderOnboarding.currentItem + 1 < introSlides.itemCount) {
                    vpSliderOnboarding.currentItem += 1
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

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.vIndicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.vIndicatorsContainer.getChildAt(i) as ImageView
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}