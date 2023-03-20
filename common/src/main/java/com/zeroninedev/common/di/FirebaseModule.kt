package com.zeroninedev.common.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.zeroninedev.common.firebase.analytic.AnalyticManager
import com.zeroninedev.common.firebase.analytic.AnalyticManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics =
        FirebaseAnalytics.getInstance(context)

    @Singleton
    @Provides
    fun provideAnalyticManager(firebaseAnalytics: FirebaseAnalytics): AnalyticManager =
        AnalyticManagerImpl(firebaseAnalytics)
}