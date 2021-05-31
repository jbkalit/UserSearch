package com.jbkalit.usersearch.presentation.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.Feed
import com.jbkalit.domain.model.User
import com.jbkalit.domain.usecase.UserUseCaseContract
import com.jbkalit.usersearch.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val userUseCase: UserUseCaseContract)
    : BaseViewModel() {

    private var keyword : String = ""
    private var page = 1

    val isError = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val isLoadMore = MutableLiveData(false)
    val isEmpty = MutableLiveData(false)

    var loadMoreError = MutableLiveData<String>()

    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>>
        get() = _user

    fun fetchUser(userName: String) {
        keyword = userName
        isLoading.value = true
        disposable = userUseCase.getUser(userName, 1, ITEM_PER_PAGE)
            .subscribeWith(object : DisposableSingleObserver<Feed>(){
                override fun onSuccess(feed: Feed) {

                    isLoading.value = false
                    isError.value = false

                    if (feed.totalCount != 0) {
                        isEmpty.value = false
                        _user.value = feed.items!!
                    } else {
                        isEmpty.value = true
                    }

                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
        page++
    }

    fun loadMore() {
        isLoadMore.value = true
        disposable = userUseCase.getUser(keyword, page, ITEM_PER_PAGE)
            .subscribeWith(object : DisposableSingleObserver<Feed>(){
                override fun onSuccess(feed: Feed) {
                    _user.value = feed.items!!
                    isLoadMore.value = false
                }

                override fun onError(e: Throwable) {
                    isLoadMore.value = false
                    loadMoreError.value = e.message
                }
            })
        page++
    }

    companion object {
        const val ITEM_PER_PAGE = 15
    }

}
