package eu.tutorials.myshoppal.data.remote.data_source.login

import eu.tutorials.myshoppal.domain.model.UserLoginModel

interface LoginRemoteDataSource {
    suspend fun loginUser(user: UserLoginModel)
}