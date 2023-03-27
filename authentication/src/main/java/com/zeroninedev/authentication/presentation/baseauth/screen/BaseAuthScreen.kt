package com.zeroninedev.authentication.presentation.baseauth.screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.zeroninedev.authentication.presentation.baseauth.screen.BaseAuthState.Error
import com.zeroninedev.authentication.presentation.baseauth.screen.BaseAuthState.LoadingGoogle
import com.zeroninedev.authentication.presentation.baseauth.screen.BaseAuthState.Ready
import com.zeroninedev.authentication.presentation.baseauth.screen.BaseAuthState.SuccessGoogle
import com.zeroninedev.authentication.presentation.baseauth.view.BaseAuthView
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthIntent
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthViewModel
import com.zeroninedev.common.constants.Constants.SERVICE_ID
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.AuthenticationScreens

@Composable
internal fun BaseAuthScreen(
    viewModel: BaseAuthViewModel,
    outerNavigator: Navigator
) {
    val context = LocalContext.current.applicationContext
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                val result = account.getResult(ApiException::class.java)
                result.idToken?.let { token -> viewModel.processIntent(BaseAuthIntent.GoogleTokenCollect(token)) }
            } catch (it: ApiException) {
                viewModel.processIntent(BaseAuthIntent.SendErrorAction(it.message))
            }
        }

    Box(contentAlignment = Alignment.Center) {

        when (val result = viewModel.screenState.collectAsState().value) {
            is Error -> {
                Toast.makeText(LocalContext.current, result.message, Toast.LENGTH_SHORT).show()
            }
            Ready -> {}
            LoadingGoogle -> { CircularProgressIndicator() }
            SuccessGoogle -> {
                Toast.makeText(
                    LocalContext.current,
                    com.zeroninedev.core_compose.R.string.success_text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        BaseAuthView(
            onRegisterClick = { outerNavigator.navigate(AuthenticationScreens.RegisterScreen.ROUTE) },
            onLoginClick = { outerNavigator.navigate(AuthenticationScreens.LoginScreen.ROUTE) },
            onGoogleSignClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(SERVICE_ID)
                    .build()

                val googleSingInClient = GoogleSignIn.getClient(context, gso)

                launcher.launch(googleSingInClient.signInIntent)
            }
        )
    }
}