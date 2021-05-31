package com.jbkalit.data.source.remote

import com.jbkalit.domain.model.Feed
import io.reactivex.Single

interface UserRemoteDataSourceContract {

    fun getUser(userName: String, page: Int, perPage: Int): Single<Feed>

}
