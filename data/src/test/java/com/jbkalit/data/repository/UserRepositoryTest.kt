package com.jbkalit.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.mock.feed
import com.jbkalit.data.mock.user
import com.jbkalit.data.source.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.repository.UserRepositoryContract
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userRemoteDataSource: UserRemoteDataSourceContract

    private lateinit var userRepository: UserRepositoryContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(userRemoteDataSource)
    }

    @Test
    fun getUser_Success_Test() {
        Mockito.`when`(userRemoteDataSource.getUser("jbkalit", 1, 15))
            .thenReturn(Single.just(feed))

        userRepository.getUser("jbkalit", 1, 15)

        Mockito.verify(userRemoteDataSource, Mockito.times(1)).getUser(
            "jbkalit",
            1,
            15)
        Assert.assertNotNull("Feed not empty", userRepository.getUser(
            "jbkalit",
            1,
            15
        ))
    }

}
