package com.adhibuchori.narumi.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.databinding.ActivityMainBinding
import com.adhibuchori.narumi.ui.authentication.login.LoginFragmentDirections
import com.adhibuchori.narumi.ui.authentication.register.RegisterFragmentDirections
import com.adhibuchori.narumi.ui.main.MainFragmentDirections
import com.adhibuchori.narumi.ui.splash.SplashFragmentDirections
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), AndroidScopeComponent {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainNavController: NavController

    override val scope: Scope by activityScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLoginObserver()
    }

    private fun setupLoginObserver() {
        val mainNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_container) as NavHostFragment
        mainNavController = mainNavHostFragment.navController

        mainViewModel.authState.observe(this) { isLogin ->
            Log.d("MainActivity Login Error: ", isLogin.toString())
            if (isLogin) {
                navigateToHome()
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToHome() {
        when (val currentDestination = mainNavController.currentDestination?.id) {
            R.id.loginFragment -> {
                mainNavController.navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            }

            R.id.splashFragment -> {
                mainNavController.navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
            }

            R.id.registerFragment -> {
                mainNavController.navigate(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
            }

            else -> {
                Log.d("MA: ", currentDestination.toString())
            }
        }
    }

    private fun navigateToLogin() {
        val currentDestination = mainNavController.currentDestination?.id
        when (currentDestination) {
            R.id.mainFragment -> {
                mainNavController.navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
            }
        }
    }
}