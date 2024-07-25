package com.adhibuchori.narumi.ui.main.detail

import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentDetailBinding
import com.adhibuchori.narumi.utils.base.BaseFragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.text.NumberFormat
import java.util.Locale

class DetailFragment :
    BaseFragment<FragmentDetailBinding, ViewModel>(FragmentDetailBinding::inflate), AndroidScopeComponent {

    private val args: DetailFragmentArgs by navArgs<DetailFragmentArgs>()

    private val detailViewModel: DetailViewModel by viewModel()
    override val scope: Scope by fragmentScope()

    override fun setupViews() {
        retrieveData()
        setupStatusBar()
        setupNavigation()
    }

    private fun setupNavigation() {
        with(binding) {
            cvDetailPageArrowBackIconContainer.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_detailFragment_to_mainFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.nav_graphs, true).build()
                )
            }
            btnDetailPageBookNow.setOnClickListener {
                val args: DetailFragmentArgs by navArgs()
                val tripData = args.tripData

                val action =
                    DetailFragmentDirections.actionDetailFragmentToCheckoutFragment(tripData)
                Navigation.findNavController(requireActivity(), R.id.fcv_container).navigate(action)
            }
        }
    }

    private fun retrieveData() {
        detailViewModel.fetchTripById(args.tripData)
        detailViewModel.tripData.observe(viewLifecycleOwner) { tripData ->
            with(binding) {
                ivDetailPageTripImage.setImageResource(tripData.tripImage)
                tvDetailPageTripName.text = tripData.tripName
                tvDetailPageTripLocation.text = tripData.tripLocation
                tvDetailPageTripCost.text = formatCurrency(tripData.tripCost)
                tvDetailPageTripDescription.text = tripData.tripDescription
                tvDetailPageTripDuration.text = tripData.tripDuration
                tvDetailPageTripDate.text = tripData.tripDate
            }
        }
    }

    private fun setupStatusBar() {
        activity?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(
                it,
                false
            )
        }
    }

    private fun formatCurrency(amount: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        format.maximumFractionDigits = 0
        return format.format(amount)
    }
}