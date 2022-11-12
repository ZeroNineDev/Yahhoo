package com.zeroninedev.core_compose.components.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zeroninedev.core_compose.ui.theme.MediumSize

/**
 * Drawer to show navigation to main screens.
 *
 * @param modifier entered modifier from other scope
 * @param onDestinationClicked callback on destination which user click
 */
@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                    .asPaddingValues()
            )
            .padding(start = MediumSize)
    ) {
    }
}