package com.adhibuchori.marketplace.ui.main.review

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentReviewBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.TitleAndIconToolbarManager
import com.adhibuchori.marketplace.utils.setupToolbar

class ReviewFragment :
    BaseFragment<FragmentReviewBinding, ViewModel>(FragmentReviewBinding::inflate) {

    override fun setupViews() {
        adjustToolbar()
        setupNavigation()
    }

    private fun setupNavigation() {
//        val navOptions = NavOptions.Builder()
//            .setPopUpTo(R.id.nav_graphs, true)
//            .build()
//
//        binding.reviewPageToolbar.ivToolbarProfile.setOnClickListener {
//            Navigation.findNavController(it).navigate(
//                R.id.action_reviewFragment_to_productDetailFragment,
//                null,
//                navOptions
//            )
//        }
    }

    private fun adjustToolbar() {
        val titleAndIconToolbarManager = TitleAndIconToolbarManager()
        val title = requireContext().getString(R.string.review)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        context?.let {
            setupToolbar(
                binding.reviewPageToolbar,
                it,
                title,
                drawable,
                titleAndIconToolbarManager
            )
        }
    }
}