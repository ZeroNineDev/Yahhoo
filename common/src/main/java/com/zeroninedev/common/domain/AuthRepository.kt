package com.zeroninedev.common.domain

interface AuthRepository {

    suspend fun registerUserByEmail(email: String, password: String) : Boolean

    suspend fun loginByEmail(email: String, password: String) : Boolean

    suspend fun loginUserByGoogleAuth(token: String)

    fun signOut()
}