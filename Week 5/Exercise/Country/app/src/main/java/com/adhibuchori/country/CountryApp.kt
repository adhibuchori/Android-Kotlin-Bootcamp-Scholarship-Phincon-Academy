package com.adhibuchori.country

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adhibuchori.country.domain.CountryModel
import com.adhibuchori.country.ui.screen.detail.DetailScreen
import com.adhibuchori.country.ui.screen.home.HomeScreen
import com.adhibuchori.country.ui.theme.CountryTheme

enum class MainScreen {
    HOME,
    DETAIL
}

@Composable
fun CountryApp(
    modifier: Modifier = Modifier,
) {
    var selectedDetail by remember { mutableStateOf(CountryModel.empty) }
    var currentScreen by remember { mutableStateOf(MainScreen.HOME) }

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                MainScreen.HOME -> {
                    HomeScreen(onClickListener = {
                        selectedDetail = it
                        currentScreen = MainScreen.DETAIL
                    })
                }
                MainScreen.DETAIL -> {
                    if (selectedDetail != CountryModel.empty) {
                        DetailScreen(
                            onBackClickListener = {
                                currentScreen = MainScreen.HOME
                                selectedDetail = CountryModel.empty
                            },
                            countryData = selectedDetail
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryAppPreview() {
    CountryTheme {
        CountryApp()
    }
}