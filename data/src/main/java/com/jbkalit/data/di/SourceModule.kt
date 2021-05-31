package com.jbkalit.data.di

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.UserService
import com.jbkalit.data.source.remote.UserRemoteDataSource
import com.jbkalit.data.source.remote.UserRemoteDataSourceContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    @Singleton
    fun providesUserRemoteDataSource(userService: UserService,
                                     schedulerProvider: BaseSchedulerProvider)
            : UserRemoteDataSourceContract = UserRemoteDataSource(userService, schedulerProvider)

}
