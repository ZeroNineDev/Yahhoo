package com.zeroninedev.authentication.domain.usecase

import com.zeroninedev.common.domain.AuthRepository
import javax.inject.Inject

internal class SendTokenUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(token: String) = repository.loginUserByGoogleAuth(token)
}