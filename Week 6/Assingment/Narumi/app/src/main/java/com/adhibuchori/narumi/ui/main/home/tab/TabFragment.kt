package com.adhibuchori.narumi.ui.main.home.tab

import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentTabBinding
import com.adhibuchori.narumi.ui.main.MainFragmentDirections
import com.adhibuchori.narumi.ui.main.home.HomeViewModel
import com.adhibuchori.narumi.ui.main.home.adapter.ListItemRowTripAdapter
import com.adhibuchori.narumi.utils.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TabFragment : BaseFragment<FragmentTabBinding, ViewModel>(FragmentTabBinding::inflate) {

    private var sectionNumber: Int = 0
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun setupViews() {
        homeViewModel.fetchAllData()
        sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 0
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        homeViewModel.allTrips.observe(viewLifecycleOwner) { trips ->
            val filteredTrips = when (sectionNumber) {
                1 -> trips
                2 -> trips.filter { it.tripCategory == "Popular" }
                3 -> trips.filter { it.tripCategory == "Recommended" }
                else -> emptyList()
            }
            binding.rvTabPage.layoutManager = LinearLayoutManager(requireContext())
            val adapter = ListItemRowTripAdapter(filteredTrips).apply {
                setOnItemClickListener { tripData ->
                    val action = MainFragmentDirections.actionMainFragmentToDetailFragment(tripData.uuid)
                    Navigation.findNavController(requireActivity(), R.id.fcv_container)
                        .navigate(action)
                }
            }

            binding.rvTabPage.adapter = adapter
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}