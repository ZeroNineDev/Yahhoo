package com.zeroninedev.core_compose.components.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    errorMessage: String?,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = com.zeroninedev.core_compose.R.drawable.ic_baseline_cloud_queue_24),
            contentDescription = stringResource(coil.compose.base.R.string.default_error_message),
            tint = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                style = typography.body1,
                color = MaterialTheme.colors.primaryVariant
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { onButtonClick.invoke() }) {
            Text(
                text = stringResource(id = com.zeroninedev.core_compose.R.string.update_button),
                color = MaterialTheme.colors.primary
            )
        }
    }
}