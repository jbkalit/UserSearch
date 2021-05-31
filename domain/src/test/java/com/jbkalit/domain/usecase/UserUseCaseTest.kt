package com.jbkalit.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.mock.feed
import com.jbkalit.domain.repository.UserRepositoryContract
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userRepository: UserRepositoryContract

    private lateinit var userUseCase: UserUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userUseCase = UserUseCase(userRepository)
    }

    @Test
    fun getUser_Success_Test() {
        Mockito.`when`(userRepository.getUser("jbkalit", 1, 15))
            .thenReturn(Single.just(feed))

        userUseCase.getUser("jbkalit", 1, 15)

        Mockito.verify(userRepository, Mockito.times(1)).getUser(
            "jbkalit",
            1,
            15
        )
        Assert.assertNotNull("Feed not empty", userUseCase.getUser(
            "jbkalit",
            1,
            15
        ))
    }

}
