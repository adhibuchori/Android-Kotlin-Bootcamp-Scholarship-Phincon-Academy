package com.adhibuchori.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.adhibuchori.composeui.ui.component.GreetingDialog
import com.adhibuchori.composeui.ui.theme.ComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
                    if (showDialog) {
                        GreetingDialog(onDismiss = { setShowDialog(false) })
                    }
                    LoginScreen(onLoginClicked = { setShowDialog(true) })
                }
            }
        }
    }
}