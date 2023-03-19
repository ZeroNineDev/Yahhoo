package com.zeroninedev.core_compose.components.image

import android.graphics.drawable.Drawable
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.ImageRequest.Listener
import coil.request.SuccessResult

internal class ImageListener(
    val onErrorResult: (String?) -> Unit,
    val onSuccessResult: (Drawable) -> Unit
): Listener {

    override fun onError(request: ImageRequest, result: ErrorResult) {
        super.onError(request, result)
        onErrorResult(result.throwable.message)
    }

    override fun onSuccess(request: ImageRequest, result: SuccessResult) {
        super.onSuccess(request, result)
        onSuccessResult(result.drawable)
    }
}