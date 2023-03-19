package com.zeroninedev.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<ScreenState>(initialValue: ScreenState): ViewModel() {

    protected val _screenState = MutableStateFlow(initialValue)
    val screenState = _screenState.asStateFlow()

    protected var screenJob: Job? = null
}