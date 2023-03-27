package com.zeroninedev.core_compose.components.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.components.button.RoundedIconButton
import com.zeroninedev.core_compose.components.button.SimpleButton
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.NormalMediumSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen

/**
 * Drawer to show navigation to main screens.
 *
 * @param modifier entered modifier from other scope
 * @param itemsList list of main routs
 * @param onDestinationClicked callback on destination which user click
 * @param onSettingClick callback on setting button click
 */
@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    itemsList: List<NavigationItemDrawerScreen>,
    onDestinationClicked: (NavigationItemDrawerScreen) -> Unit,
    onUserClicked: () -> Unit,
    onSettingClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                    .asPaddingValues()
            )
    ) {
        SimpleButton(text = "Рег", onButtonClick = onUserClicked)

        Spacer(modifier = Modifier.height(MediumSize))
        Text(
            text = stringResource(id = com.zeroninedev.navigation.R.string.manga_drawer_title),
            style = yahhooTypography.h5,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = NormalMediumSize)
        )
        Spacer(modifier = Modifier.height(SmallSize))
        Divider(
            color = MaterialTheme.colors.primaryVariant
        )
        itemsList.forEach { item ->
            Spacer(modifier = Modifier.height(MediumSize))
            Row(horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDestinationClicked(item) }) {
                Spacer(modifier = Modifier.width(MediumSize))
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.route,
                    tint = MaterialTheme.colors.primaryVariant
                )
                Spacer(modifier = Modifier.width(NormalMediumSize))
                Text(
                    text = stringResource(id = item.title),
                    style = yahhooTypography.h6,
                    color = MaterialTheme.colors.primaryVariant
                )
            }
        }
        Spacer(modifier = Modifier.weight(1F))

        RoundedIconButton(icon = com.zeroninedev.core_compose.R.drawable.settings_icon, onClick = onSettingClick)
    }
}