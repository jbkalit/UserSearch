package com.jbkalit.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.mock.feed
import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.scheduler.SchedulerProvider
import com.jbkalit.data.service.UserService
import com.jbkalit.data.source.remote.UserRemoteDataSource
import com.jbkalit.data.source.remote.UserRemoteDataSourceContract
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userService: UserService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var userRemoteDataSource: UserRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        userRemoteDataSource = UserRemoteDataSource(
            userService,
            schedulerProvider
        )
    }

    @Test
    fun getUser_Success_Test() {
        Mockito.`when`(userService.getUsers("jbkalit", 1, 15))
            .thenReturn(Single.just(feed))

        userRemoteDataSource.getUser("jbkalit", 1, 15)

        Mockito.verify(userService, Mockito.times(1)).getUsers(
            "jbkalit",
            1,
            15
        )
        Assert.assertNotNull("Feed not empty", userRemoteDataSource.getUser(
            "jbkalit",
            1,
            15
        ))
    }

}
