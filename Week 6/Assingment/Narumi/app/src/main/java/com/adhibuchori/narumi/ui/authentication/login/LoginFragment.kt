package com.adhibuchori.narumi.ui.authentication.login

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
import com.adhibuchori.narumi.databinding.FragmentLoginBinding
import com.adhibuchori.narumi.utils.base.BaseFragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class LoginFragment : BaseFragment<FragmentLoginBinding, ViewModel>(FragmentLoginBinding::inflate),
    AndroidScopeComponent {

    private val loginViewModel: LoginViewModel by viewModel()
    override val scope: Scope by fragmentScope()

    override fun setupViews() {
        setupRegisterNavigation()
        setupLoginListener()
    }

    private fun setupLoginListener() {
        with(binding) {
            btnLoginPageLogin.setOnClickListener {
                val email = tietLoginPageEmail.text.toString()
                val password = tietLoginPagePassword.text.toString()

                when {
                    email.isEmpty() -> {
                        tilLoginPageEmail.error = "Please enter an email"
                    }

                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        tilLoginPageEmail.error = "Invalid email format"
                    }

                    password.isEmpty() -> {
                        tilLoginPagePassword.error = "Please enter a password"
                    }

                    password.length < 8 -> {
                        tilLoginPagePassword.error = "Password must be at least 8 characters long"
                    }

                    else -> {
                        apply {
                            tietLoginPageEmail.onEditorAction(EditorInfo.IME_ACTION_DONE)
                            tietLoginPagePassword.onEditorAction(EditorInfo.IME_ACTION_DONE)
                        }

                        loginViewModel.login(email = email, password = password)
                    }
                }
            }
        }
    }

    private fun setupRegisterNavigation() {
        val registerNow = "Register now"
        val formattedString = getString(R.string.no_account, registerNow)

        val spannableStringBuilder = SpannableStringBuilder(formattedString)

        val termsStart = formattedString.indexOf(registerNow)
        val termsEnd = termsStart + registerNow.length

        val termsClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val navController = Navigation.findNavController(widget)
                navController.navigate(R.id.action_loginFragment_to_registerFragment)
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

        with(binding.tvLoginPageNoAccount) {
            text = spannableStringBuilder
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }
}