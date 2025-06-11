package com.example.tawuniya.di

import com.example.tawuniya.data.source.remote.TawuniyaApiService
import com.example.tawuniya.data.source.remote.TawuniyaRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideTawuniyaApiService(): TawuniyaApiService = TawuniyaRetrofitClient.create()
}