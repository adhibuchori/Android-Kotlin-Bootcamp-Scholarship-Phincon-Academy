package com.adhibuchori.marketplace.ui.main.store

import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentStoreBinding
import com.adhibuchori.marketplace.ui.main.BottomNavigationContainerFragmentDirections
import com.adhibuchori.marketplace.ui.main.store.adapter.GridItemStoreAdapter
import com.adhibuchori.marketplace.ui.main.store.adapter.ListItemStoreAdapter
import com.adhibuchori.marketplace.ui.main.store.dummyData.StoreData
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.setupToolbar
import com.adhibuchori.marketplace.utils.TitleOnlyToolbarManager

class StoreFragment : BaseFragment<FragmentStoreBinding, ViewModel>(FragmentStoreBinding::inflate) {

    private lateinit var listStore: List<StoreData>
    private var isListView = true

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
        setupRecyclerView()
    }

    private fun adjustToolbar() {
        val titleOnlyToolbarManager = TitleOnlyToolbarManager()
        val title = requireContext().getString(R.string.user_name)
        context?.let {
            setupToolbar(
                binding.storePageToolbar,
                it,
                title,
                null,
                titleOnlyToolbarManager
            )
        }
    }

    private fun setupNavigation() {
        with(binding) {

            sbStorePage.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_bottomNavigationContainerFragment_to_searchFragment)
            )

            ivStorePageSetLayoutView.setOnClickListener {
                toggleRecyclerViewLayout()
            }

            with(storePageToolbar) {
                ivToolbarNotification.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_bottomNavigationContainerFragment_to_notificationFragment)
                )
                ivToolbarShoppingCart.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_bottomNavigationContainerFragment_to_cartFragment)
                )
            }

        }
    }

    private fun toggleRecyclerViewLayout() {
        if (isListView) {
            with(binding) {
                with(rvFragmentPageListView) {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    val gridAdapter = GridItemStoreAdapter(listStore).apply {
                        setOnItemClickListener { storeData ->
                            val action = BottomNavigationContainerFragmentDirections
                                .actionBottomNavigationContainerFragmentToProductDetailFragment(
                                    storeData
                                )
                            findNavController().navigate(action)
                        }
                    }
                    adapter = gridAdapter
                }
                ivStorePageSetLayoutView.setImageResource(R.drawable.ic_list_view)
            }
        } else {
            with(binding) {
                with(rvFragmentPageListView) {
                    layoutManager = LinearLayoutManager(requireContext())
                    val listAdapter = ListItemStoreAdapter(listStore).apply {
                        setOnItemClickListener { storeData ->
                            val action = BottomNavigationContainerFragmentDirections
                                .actionBottomNavigationContainerFragmentToProductDetailFragment(
                                    storeData
                                )
                            findNavController().navigate(action)
                        }
                    }
                    adapter = listAdapter
                }
                ivStorePageSetLayoutView.setImageResource(R.drawable.ic_grid_view)
            }
        }
        isListView = !isListView
    }


    private fun setupRecyclerView() {
        listStore = getListStoreData()

        val listAdapter = ListItemStoreAdapter(listStore).apply {
            setOnItemClickListener { storeData ->
                val action = BottomNavigationContainerFragmentDirections
                    .actionBottomNavigationContainerFragmentToProductDetailFragment(storeData)
                findNavController().navigate(action)
            }
        }

        with(binding.rvFragmentPageListView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun getListStoreData(): List<StoreData> {
        val productImages = resources.getStringArray(R.array.product_images)
        val productNames = resources.getStringArray(R.array.product_names)
        val productPrices = resources.getStringArray(R.array.product_prices)
        val productStores = resources.getStringArray(R.array.product_stores)
        val productReviews = resources.getStringArray(R.array.product_reviews)

        val listStore = mutableListOf<StoreData>()
        for (i in productImages.indices) {
            val storeData = StoreData(
                productImages[i],
                productNames[i],
                productPrices[i],
                productStores[i],
                productReviews[i]
            )
            listStore.add(storeData)
        }
        return listStore
    }
}