package com.zeroninedev.core_compose.components.text

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun VersionTextView(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = getAppVersion(LocalContext.current),
        fontSize = 12.sp,
        color = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center
    )
}

internal fun getAppVersion(context: Context): String {
    var version = ""
    try {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        version = pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return version
}