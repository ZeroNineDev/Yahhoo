package com.zeroninedev.yahhoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.zeroninedev.core_compose.ui.theme.YahhooTheme
import com.zeroninedev.yahhoo.navigation.MainNavigationGraph

@ExperimentalAnimationApi
@ExperimentalComposeApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            YahhooTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainNavigationGraph()
                }
            }
        }
    }
}