package com.jbkalit.domain.repository

import com.jbkalit.domain.model.Feed
import io.reactivex.Single

interface UserRepositoryContract {

    fun getUser(userName: String, page: Int, perPage: Int): Single<Feed>

}
