package com.jbkalit.domain.usecase

import com.jbkalit.domain.repository.UserRepositoryContract
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepositoryContract)
    : UserUseCaseContract {

    override fun getUser(userName: String,
                         page: Int,
                         perPage: Int)
    = userRepository.getUser(userName, page, perPage)

}
