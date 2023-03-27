package com.zeroninedev.common.firebase.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.zeroninedev.common.domain.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    private val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    val isAuthentificated: Boolean
        get() = currentUser != null

    override suspend fun registerUserByEmail(email: String, password: String): Boolean {
        isAuthentificated
        val userCreateResult = firebaseAuth.createUserWithEmailAndPassword(email, password)
        userCreateResult.addOnCompleteListener {task ->
            Log.d(LOG, "registerUserByEmail $task")
        }
        return userCreateResult.await() != null
    }

    override suspend fun loginByEmail(email: String, password: String) : Boolean  {
        val userLoginResult = firebaseAuth.signInWithEmailAndPassword(email, password)
        userLoginResult.addOnCompleteListener { task ->
            Log.d(LOG, "loginByEmail $task")
        }
        return userLoginResult.await() != null
    }

    override suspend fun loginUserByGoogleAuth(token: String) {
        val credentials = GoogleAuthProvider.getCredential(token, null)
        val result = firebaseAuth.signInWithCredential(credentials)
        result.addOnCompleteListener { task ->
            Log.d(LOG, "loginUserByGoogleAuth $task")
        }
        result.await()
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    private companion object {

        const val LOG = "AuthRepositoryImpl"
    }
}