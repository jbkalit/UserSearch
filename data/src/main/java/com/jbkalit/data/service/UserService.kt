package com.jbkalit.data.service

import com.jbkalit.domain.model.Feed
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    fun getUsers(@Query(value = "q") userName: String,
                 @Query(value = "page")page:Int,
                 @Query(value = "per_page") perPage:Int) : Single<Feed>

}
