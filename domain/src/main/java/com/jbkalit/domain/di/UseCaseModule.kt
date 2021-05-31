package com.jbkalit.domain.di

import com.jbkalit.domain.repository.UserRepositoryContract
import com.jbkalit.domain.usecase.UserUseCase
import com.jbkalit.domain.usecase.UserUseCaseContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesUserUseCasePost(userRepository: UserRepositoryContract)
            : UserUseCaseContract = UserUseCase(userRepository)

}
