package com.adhibuchori.composeui.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adhibuchori.composeui.ui.theme.ComposeUITheme
import com.adhibuchori.composeui.ui.theme.Typography

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    title: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = title,
            style = Typography.titleMedium,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

@Composable
@Preview
fun ShowPrimaryButtonPreview() {
    ComposeUITheme {
        PrimaryButton(title = "Login")
    }
}