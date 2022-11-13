package com.zeroninedev.yahhoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowCompat
import com.zeroninedev.core_compose.components.drawer.NavigationDrawer
import com.zeroninedev.core_compose.components.toolbar.TopToolbar
import com.zeroninedev.core_compose.ui.theme.YahhooTheme
import kotlinx.coroutines.launch
import ru.zeroninedev.navigation.destination.navigationItemDrawerList

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            YahhooTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    topBar = {
                        TopToolbar(screenTitle = "Fucking Screen") {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    },
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        NavigationDrawer(itemsList = navigationItemDrawerList()) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }

                    }
                ) {

                }
            }
        }
    }
}