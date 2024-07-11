package com.adhibuchori.composeui.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.adhibuchori.composeui.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    title: String = "",
    value: String = "",
    onTextChange: (String) -> Unit = {},
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { onTextChange.invoke(it) },
        label = { Text("Password") },
        visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = isPasswordVisible.not() }) {
                Icon(
                    painter = painterResource(id = if (isPasswordVisible) R.drawable.ic_invisible else R.drawable.ic_visible),
                    contentDescription = "Toggle Password"
                )
            }

        },
        modifier = Modifier.fillMaxWidth()
    )
}