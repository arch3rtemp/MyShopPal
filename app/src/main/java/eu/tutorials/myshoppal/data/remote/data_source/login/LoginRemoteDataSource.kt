package eu.tutorials.myshoppal.data.remote.data_source.login

import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel

interface LoginRemoteDataSource {
    suspend fun loginUser(user: UserLoginDataModel)
}