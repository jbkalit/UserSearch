package com.jbkalit.data.repository

import com.jbkalit.data.source.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.model.Feed
import com.jbkalit.domain.repository.UserRepositoryContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(private val userRemoteDataSource: UserRemoteDataSourceContract)
    : UserRepositoryContract {

    override fun getUser(userName: String, page: Int, perPage: Int): Single<Feed> {
        return userRemoteDataSource.getUser(userName, page, perPage)
    }

}
