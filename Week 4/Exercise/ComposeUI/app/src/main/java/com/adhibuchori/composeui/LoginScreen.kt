package com.adhibuchori.composeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adhibuchori.composeui.ui.component.PasswordTextField
import com.adhibuchori.composeui.ui.component.PrimaryButton
import com.adhibuchori.composeui.ui.theme.ComposeUITheme

@Composable
fun LoginScreen(onLoginClicked: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login Screen",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Welcome to Login Screen Compose UI",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Enter email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            placeholder = { Text("Enter username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            value = password,
            onTextChange = { password = it }
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(
            title = "Login",
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()
        ) {
            onLoginClicked()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ComposeUITheme {
        LoginScreen(onLoginClicked = {})
    }
}