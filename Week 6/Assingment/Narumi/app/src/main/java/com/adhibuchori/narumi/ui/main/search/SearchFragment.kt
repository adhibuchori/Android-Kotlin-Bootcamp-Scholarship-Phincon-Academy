package com.adhibuchori.narumi.ui.main.search

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentSearchBinding
import com.adhibuchori.narumi.ui.main.home.adapter.ListItemRowTripAdapter
import com.adhibuchori.narumi.utils.base.BaseFragment
import com.adhibuchori.narumi.utils.gone
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SearchFragment :
    BaseFragment<FragmentSearchBinding, ViewModel>(FragmentSearchBinding::inflate),
    AndroidScopeComponent {

    override val scope: Scope by fragmentScope()
    private val searchViewModel: SearchViewModel by viewModel()

    override fun setupViews() {
        searchViewModel.fetchAllTrips()

        setupNavigation()
        setupSearch()
        setupRecyclerView()
        setupStatusBar()

        with(binding) {
            svSearchPage.post {
                svSearchPage.requestFocus()
                showKeyboard()
            }
            rvSearchPage.gone()
        }
    }

    @Suppress("DEPRECATION")
    private fun showKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun setupNavigation() {
        with(binding) {
            ivSearchPageArrowBackIcon.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.fcv_container)
                    .navigate(
                        R.id.action_searchFragment_to_mainFragment,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.nav_graphs, true).build()
                    )
            }
        }
    }

    private fun setupRecyclerView() {
        searchViewModel.filterTrips.observe(viewLifecycleOwner) { trips ->
            with(binding.rvSearchPage) {
                layoutManager = LinearLayoutManager(requireContext())
                val listAdapter = ListItemRowTripAdapter(trips).apply {
                    setOnItemClickListener { tripData ->
                        val action =
                            SearchFragmentDirections.actionSearchFragmentToDetailFragment(tripData.uuid)
                        Navigation.findNavController(requireActivity(), R.id.fcv_container)
                            .navigate(
                                action,
                                NavOptions.Builder()
                                    .setPopUpTo(R.id.searchFragment, true)
                                    .build()
                            )
                    }
                }
                adapter = listAdapter
                listAdapter.updateList(trips)
            }
        }
    }

    private fun setupSearch() {
        binding.svSearchPage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handleSearchViewState(true)
                query?.let {
                    searchViewModel.searchTrips(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handleSearchViewState(newText?.isNotEmpty() == true)
                newText?.let {
                    searchViewModel.searchTrips(it)
                }
                return true
            }
        })
    }

    private fun handleSearchViewState(isActive: Boolean) {
        binding.rvSearchPage.visibility = if (isActive) View.VISIBLE else View.GONE
    }

    private fun setupStatusBar() {
        activity?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(
                it,
                true
            )
        }
    }
}