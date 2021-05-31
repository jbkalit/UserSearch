package com.jbkalit.data.di

import com.jbkalit.data.repository.UserRepository
import com.jbkalit.data.source.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.repository.UserRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(userRemoteDataSource: UserRemoteDataSourceContract)
            : UserRepositoryContract = UserRepository(userRemoteDataSource)

}
