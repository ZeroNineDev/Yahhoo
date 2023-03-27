package com.zeroninedev.authentication.domain.usecase

import com.zeroninedev.common.domain.AuthRepository
import javax.inject.Inject

internal class RegisterUserUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = repository.registerUserByEmail(email, password)
}