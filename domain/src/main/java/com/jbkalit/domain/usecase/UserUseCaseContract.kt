package com.jbkalit.domain.usecase

import com.jbkalit.domain.model.Feed
import io.reactivex.Single

interface UserUseCaseContract {

    fun getUser(userName: String, page: Int, perPage: Int): Single<Feed>

}
