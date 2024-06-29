package com.adhibuchori.marketplace.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentBottomNavigationContainerBinding
import com.adhibuchori.marketplace.ui.main.home.HomeFragment
import com.adhibuchori.marketplace.ui.main.store.StoreFragment
import com.adhibuchori.marketplace.ui.main.transaction.transaction.TransactionFragment
import com.adhibuchori.marketplace.ui.main.wishlist.WishlistFragment
import com.adhibuchori.marketplace.utils.BaseFragment

class BottomNavigationContainerFragment :
    BaseFragment<FragmentBottomNavigationContainerBinding, ViewModel>(
        FragmentBottomNavigationContainerBinding::inflate
    ) {

    private var currentFragmentTag: String? = null

    override fun setupViews() {
        setBottomNavigation()
        if (childFragmentManager.fragments.isEmpty()) {
            loadFragment(HomeFragment(), HomeFragment::class.java.simpleName)
        }
    }

    private fun setBottomNavigation() {
        @Suppress("DEPRECATION")
        binding.bnvNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            var tag: String? = null
            when (item.itemId) {
                R.id.navigation_home -> {
                    selectedFragment = HomeFragment()
                    tag = HomeFragment::class.java.simpleName
                }

                R.id.navigation_store -> {
                    selectedFragment = StoreFragment()
                    tag = StoreFragment::class.java.simpleName
                }

                R.id.navigation_wishlist -> {
                    selectedFragment = WishlistFragment()
                    tag = WishlistFragment::class.java.simpleName
                }

                R.id.navigation_transaction -> {
                    selectedFragment = TransactionFragment()
                    tag = TransactionFragment::class.java.simpleName
                }
            }
            if (selectedFragment != null && tag != null) {
                loadFragment(selectedFragment, tag)
                true
            } else {
                false
            }
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        if (tag != currentFragmentTag) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fcv_container, fragment, tag)
                .addToBackStack(tag)
                .commit()
            currentFragmentTag = tag
        }
    }
}