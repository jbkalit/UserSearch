package com.jbkalit.data.source.remote

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.UserService
import com.jbkalit.domain.model.Feed
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userService: UserService,
                                               private val schedulerProvider: BaseSchedulerProvider)
    : UserRemoteDataSourceContract {

    override fun getUser(userName: String, page: Int, perPage: Int): Single<Feed> {
        return userService.getUsers(userName, page, perPage)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
