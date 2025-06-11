package com.example.tawuniya.di

import com.example.tawuniya.data.repo.HomeRepository
import com.example.tawuniya.data.repo.HomeRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImp: HomeRepositoryImp): HomeRepository

}