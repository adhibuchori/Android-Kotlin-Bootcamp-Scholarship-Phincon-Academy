package com.adhibuchori.narumi.ui.main.profile

import androidx.lifecycle.ViewModel
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentProfileBinding
import com.adhibuchori.narumi.utils.base.BaseFragment
import com.adhibuchori.narumi.utils.gone
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ViewModel>(FragmentProfileBinding::inflate),
    AndroidScopeComponent {

    private val profileViewModel: ProfileViewModel by viewModel()
    override val scope: Scope by fragmentScope()

    override fun setupViews() {
        setupToolbar()
        initView()
    }

    private fun initView() {
        profileViewModel.fetchProfileData()

        profileViewModel.userData.observe(viewLifecycleOwner) { userModel ->
            binding.apply {
                tvProfilePageUsernameData.text = userModel.username
                tvProfilePageEmailData.text = userModel.email
                btnProfilePageLogout.setOnClickListener { profileViewModel.logout() }
            }
        }
    }

    private fun setupToolbar() {
        with(binding.profilePageToolbar) {
            tvToolbarTitle.text = getString(R.string.navigation_profile)
            ivToolbarArrowBackIcon.gone()
        }
    }
}