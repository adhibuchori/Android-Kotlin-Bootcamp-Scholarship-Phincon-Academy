package com.adhibuchori.narumi.ui.main.home

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentHomeBinding
import com.adhibuchori.narumi.ui.main.MainFragmentDirections
import com.adhibuchori.narumi.ui.main.home.adapter.ListItemRowTripPopularDestinationAdapter
import com.adhibuchori.narumi.ui.main.home.tab.adapter.SectionsPagerAdapter
import com.adhibuchori.narumi.utils.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, ViewModel>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onResume() {
        Firebase.analytics.logEvent(
            "screen_view", bundleOf("screen_name" to "Home")
        )
        super.onResume()
    }

    override fun setupViews() {
        homeViewModel.fetchAllData()
        observeData()
        setupTabLayout()
        setupNavigation()
    }

    private fun setupNavigation() {
        with(binding) {
            sbHomePage.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.fcv_container)
                    .navigate(R.id.action_mainFragment_to_searchFragment)
            }
        }
    }

    private fun observeData() {
        with(homeViewModel) {
            allTrips.observe(viewLifecycleOwner) { trips ->
                val filteredTrips = trips.filter { it.tripCategory == "Popular" }
                with(binding.rvHomePagePopularDestination) {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    val listAdapter = ListItemRowTripPopularDestinationAdapter(filteredTrips).apply {
                        setOnItemClickListener { tripData ->
                            Firebase.analytics.logEvent(
                                FirebaseAnalytics.Event.SELECT_ITEM,
                                bundleOf(
                                    "item_image" to tripData.tripImage,
                                    "item_name" to tripData.tripName
                                )
                            )

                            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(tripData.uuid)
                            Navigation.findNavController(requireActivity(), R.id.fcv_container).navigate(action)
                        }
                    }
                    adapter = listAdapter
                }
                userData.observe(viewLifecycleOwner) {
                    val welcomeMessage = resources.getString(R.string.welcome_message, it.username)
                    binding.tvHomePageWelcomeMessage.text = welcomeMessage
                }
            }
        }
    }

    private fun setupTabLayout() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.vpHomePage.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tlHomePage
        TabLayoutMediator(tabs, binding.vpHomePage) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        (activity as AppCompatActivity).supportActionBar?.elevation = 0f
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_all,
            R.string.tab_popular,
            R.string.tab_recommended
        )
    }
}