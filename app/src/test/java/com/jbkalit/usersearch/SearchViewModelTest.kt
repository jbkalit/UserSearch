package com.jbkalit.usersearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.model.Feed
import com.jbkalit.domain.usecase.UserUseCaseContract
import com.jbkalit.usersearch.mock.emptyFeed
import com.jbkalit.usersearch.mock.feed
import com.jbkalit.usersearch.presentation.search.viewmodel.SearchViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testFeed: Single<Feed>? = null

    private var page = 1

    @Mock
    private lateinit var searchUseCase: UserUseCaseContract

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        searchViewModel = SearchViewModel(searchUseCase)
    }

    @Test
    fun getUser_Success_Test() {
        testFeed = Single.just(feed)
        `when`(searchUseCase.getUser("jbkalit", 1, 15)).thenReturn(testFeed)
        searchViewModel.fetchUser("jbkalit")
        Assert.assertEquals(1, searchViewModel.user.value?.size)
        Assert.assertEquals(false, searchViewModel.isError.value)
        Assert.assertEquals(false, searchViewModel.isLoading.value)
    }

    @Test
    fun getUser_ErrorShow_Test() {
        testFeed = Single.error(Throwable())
        `when`(searchUseCase.getUser("jbkalit", 1, 15)).thenReturn(testFeed)
        searchViewModel.fetchUser("jbkalit")
        Assert.assertEquals(true, searchViewModel.isError.value)
    }

    @Test
    fun getUser_LoadingShow_Test() {
        testFeed = Single.never()
        `when`(searchUseCase.getUser("jbkalit", 1, 15)).thenReturn(testFeed)
        searchViewModel.fetchUser("jbkalit")
        Assert.assertEquals(true, searchViewModel.isLoading.value)
    }

    @Test
    fun getUser_EmptyShow_Test() {
        testFeed = Single.just(emptyFeed)
        `when`(searchUseCase.getUser("jbkalit", 1, 15)).thenReturn(testFeed)
        searchViewModel.fetchUser("jbkalit")
        Assert.assertEquals(true, searchViewModel.isEmpty.value)
    }

}
