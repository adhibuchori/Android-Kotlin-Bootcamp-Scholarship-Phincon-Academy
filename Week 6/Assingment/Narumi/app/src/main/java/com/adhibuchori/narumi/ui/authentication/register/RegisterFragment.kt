package com.adhibuchori.narumi.ui.authentication.register

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.FragmentRegisterBinding
import com.adhibuchori.narumi.utils.base.BaseFragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, ViewModel>(FragmentRegisterBinding::inflate),
    AndroidScopeComponent {

    override val scope: Scope by fragmentScope()
    private val registerViewModel: RegisterViewModel by viewModel()

    override fun setupViews() {
        setupNavigation()
        setupRegisterListener()
    }

    private fun setupNavigation() {
        binding.ivRegisterPageArrowBackIcon.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigateUp()
        }
        setupLoginNavigation()
    }

    private fun setupLoginNavigation() {
        val loginNow = "Login now"
        val formattedString = getString(R.string.no_account, loginNow)

        val spannableStringBuilder = SpannableStringBuilder(formattedString)

        val termsStart = formattedString.indexOf(loginNow)
        val termsEnd = termsStart + loginNow.length

        val termsClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val navController = Navigation.findNavController(widget)
                navController.navigate(R.id.action_registerFragment_to_loginFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#E67E9E")
                ds.isUnderlineText = false
            }
        }

        val boldSpan = StyleSpan(Typeface.BOLD)
        spannableStringBuilder.setSpan(
            boldSpan,
            termsStart,
            termsEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringBuilder.setSpan(
            termsClickable,
            termsStart,
            termsEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        with(binding.tvRegisterPageHaveAccount) {
            text = spannableStringBuilder
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun setupRegisterListener() {
        with(binding) {
            btnRegisterPageRegister.setOnClickListener {
                val username = tietRegisterPageUsername.text.toString()
                val email = tietRegisterPageEmail.text.toString()
                val password = tietRegisterPagePassword.text.toString()

                when {
                    username.isEmpty() -> {
                        tilRegisterPageUsername.error = "Please enter a username"
                    }

                    email.isEmpty() -> {
                        tilRegisterPageEmail.error = "Please enter an email"
                    }

                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        tilRegisterPageEmail.error = "Invalid email format"
                    }

                    password.isEmpty() -> {
                        tilRegisterPagePassword.error = "Please enter a password"
                    }

                    password.length < 8 -> {
                        tilRegisterPagePassword.error =
                            "Password must be at least 8 characters long"
                    }

                    else -> {
                        apply {
                            tietRegisterPageEmail.onEditorAction(EditorInfo.IME_ACTION_DONE)
                            tietRegisterPagePassword.onEditorAction(EditorInfo.IME_ACTION_DONE)
                        }
                        registerViewModel.register(username, email, password)
                    }
                }
            }
        }
    }
}