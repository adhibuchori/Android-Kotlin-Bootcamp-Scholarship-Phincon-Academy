package com.adhibuchori.animationapp

import android.animation.ObjectAnimator
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.animationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listQuotes = ArrayList<DisneyQuote>()

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4F,
                    0.0F
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4F,
                    0.0F
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adjustStatusBarColor()

        binding.rvDisneyQuotes.setHasFixedSize(true)
        listQuotes.addAll(getListQuotes())
        showRecyclerList()
    }

    private fun adjustStatusBarColor() {
        val statusBarColor = if (isNightMode()) {
            ContextCompat.getColor(this, R.color.black)
        } else {
            ContextCompat.getColor(this, R.color.light_primary_color)
        }

        setStatusBarColor(statusBarColor)
    }

    private fun setStatusBarColor(color: Int) {
        val window: Window = this.window

        @Suppress("DEPRECATION")
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

        @Suppress("DEPRECATION")
        if (isNightMode()) {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

    private fun isNightMode(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun getListQuotes(): ArrayList<DisneyQuote> {
        val quoteTexts = resources.getStringArray(R.array.quotes_text)
        val quoteAuthors = resources.getStringArray(R.array.quotes_author)
        val quoteSources = resources.getStringArray(R.array.quotes_source)

        val listQuote = ArrayList<DisneyQuote>()
        for (i in quoteTexts.indices) {
            val quote = DisneyQuote(quoteTexts[i], quoteAuthors[i], quoteSources[i])
            listQuote.add(quote)
        }
        return listQuote
    }

    private fun showRecyclerList() {
        binding.rvDisneyQuotes.layoutManager = LinearLayoutManager(this)
        val quoteAdapter = ItemDisneyQuotesAdapter(listQuotes)
        binding.rvDisneyQuotes.adapter = quoteAdapter
    }
}