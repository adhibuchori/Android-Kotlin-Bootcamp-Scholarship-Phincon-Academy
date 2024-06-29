package com.adhibuchori.marketplace.ui.authentication.registerProfile

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentRegisterProfileBinding
import com.adhibuchori.marketplace.utils.BaseFragment
import com.adhibuchori.marketplace.utils.ToolbarManager
import java.util.Locale

class RegisterProfileFragment :
    BaseFragment<FragmentRegisterProfileBinding, ViewModel>(FragmentRegisterProfileBinding::inflate) {

    override fun setupViews() {
        setupNavigation()
        adjustToolbar()
        adjustSpannableString()
    }

    private fun adjustSpannableString() {
        val termsConditions: String
        val privacyPolicy: String

        val currentLocale = Locale.getDefault()
        if (currentLocale.language == "id") {
            termsConditions = "Syarat & Ketentuan"
            privacyPolicy = "Kebijakan Privasi"
        } else {
            termsConditions = "Terms & Conditions"
            privacyPolicy = "Privacy Policy"
        }

        val formattedString = getString(R.string.login_terms, termsConditions, privacyPolicy)

        val spannableStringBuilder = SpannableStringBuilder(formattedString)

        val termsStart = formattedString.indexOf(termsConditions)
        val termsEnd = termsStart + termsConditions.length

        val termsClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://policies.google.com/terms?hl=en-US")
                )
                widget.context.startActivity(browserIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#6750A4")
                ds.isUnderlineText = false
            }
        }
        spannableStringBuilder.setSpan(
            termsClickable,
            termsStart,
            termsEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val privacyStart = formattedString.indexOf(privacyPolicy)
        val privacyEnd = privacyStart + privacyPolicy.length

        val privacyClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://policies.google.com/privacy?hl=en-US")
                )
                widget.context.startActivity(browserIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#6750A4")
                ds.isUnderlineText = false
            }
        }
        spannableStringBuilder.setSpan(
            privacyClickable,
            privacyStart,
            privacyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        with(binding.tvRegisterPageRegisterTerms) {
            text = spannableStringBuilder
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun adjustToolbar() {
        val toolbarManager = ToolbarManager()
        val title = requireContext().getString(R.string.register)
        context?.let {
            com.adhibuchori.marketplace.utils.setupToolbar(
                binding.registerProfilePageToolbar,
                it,
                title,
                null,
                toolbarManager
            )
        }
    }

    private fun setupNavigation() {
        with(binding) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graphs, true)
                .build()

            btnRegisterPageRegister.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_registerProfileFragment_to_loginFragment,
                    null,
                    navOptions
                )
            }
        }
    }
}
