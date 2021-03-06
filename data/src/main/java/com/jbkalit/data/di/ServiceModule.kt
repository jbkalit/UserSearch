package com.jbkalit.data.di

import com.jbkalit.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit)
            : UserService = retrofit.create(UserService::class.java)

}
