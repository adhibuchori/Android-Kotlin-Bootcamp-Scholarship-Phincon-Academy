package com.adhibuchori.marketplace.ui.authentication.registerProfile

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentRegisterProfileBinding

class RegisterProfileFragment : Fragment() {

    private var _binding: FragmentRegisterProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpNavigation()
    }

    private fun setUpToolbar() {
        with(binding.toolbar) {
            val layoutParams =
                tvToolbarTitle.layoutParams as androidx.appcompat.widget.Toolbar.LayoutParams
            layoutParams.gravity = Gravity.CENTER
            tvToolbarTitle.layoutParams = layoutParams

            tvToolbarTitle.text = getString(R.string.profile)
            ivToolbarProfile.visibility = View.GONE
            ivToolbarNotification.visibility = View.GONE
            ivToolbarShoppingCart.visibility = View.GONE
            ivToolbarMenu.visibility = View.GONE
        }
    }

    private fun setUpNavigation() {
        with(binding) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graphs, true)
                .build()

            btnRegister.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_registerProfileFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
