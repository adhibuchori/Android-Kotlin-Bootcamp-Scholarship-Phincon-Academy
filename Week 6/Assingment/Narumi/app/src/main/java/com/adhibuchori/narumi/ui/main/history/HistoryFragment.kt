package com.adhibuchori.narumi.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentHistoryBinding
import com.adhibuchori.narumi.domain.transaction.TransactionModel
import com.adhibuchori.narumi.ui.main.history.adapter.ListItemRowHistoryAdapter
import com.adhibuchori.narumi.utils.base.BaseFragment
import com.adhibuchori.narumi.utils.gone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, ViewModel>(FragmentHistoryBinding::inflate),
    AndroidScopeComponent {

    private val historyViewModel: HistoryViewModel by viewModel()
    override val scope: Scope by fragmentScope()

    override fun setupViews() {
        setupToolbar()
        observeData()
    }

    private fun setupToolbar() {
        with(binding.historyPageToolbar) {
            tvToolbarTitle.text = getString(R.string.navigation_history)
            ivToolbarArrowBackIcon.gone()
        }
    }

    private fun setupRecyclerView(data: List<TransactionModel>) {
        with(binding.rvHistoryPage) {
            layoutManager = LinearLayoutManager(requireContext())
            val listAdapter = ListItemRowHistoryAdapter(data)
            adapter = listAdapter
        }
    }

    private fun observeData() {
        lifecycleScope.launch(Dispatchers.Main) {
            historyViewModel.historyData.collect {
                setupRecyclerView(it)
            }
        }
    }
}