package eu.tutorials.myshoppal.data.remote.data_source.login

import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel

interface LoginRemoteDataSource {
    suspend fun authUser(user: UserLoginModel)
    suspend fun retrieveUser(): UserModel
}