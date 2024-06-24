package com.adhibuchori.marketplace.ui.authentication.registerProfile

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.adhibuchori.marketplace.R
import com.adhibuchori.marketplace.databinding.FragmentRegisterProfileBinding
import java.util.Locale

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
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://policies.google.com/terms?hl=en-US"))
                widget.context.startActivity(browserIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#6750A4")
                ds.isUnderlineText = false
            }
        }
        spannableStringBuilder.setSpan(termsClickable, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val privacyStart = formattedString.indexOf(privacyPolicy)
        val privacyEnd = privacyStart + privacyPolicy.length

        val privacyClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://policies.google.com/privacy?hl=en-US"))
                widget.context.startActivity(browserIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#6750A4")
                ds.isUnderlineText = false
            }
        }
        spannableStringBuilder.setSpan(privacyClickable, privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        with(binding.tvRegisterPageRegisterTerms) {
            text = spannableStringBuilder
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun setUpToolbar() {
        with(binding.toolbarRegisterProfilePage) {
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

            btnRegisterPageRegister.setOnClickListener {
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
