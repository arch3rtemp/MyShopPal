package eu.tutorials.myshoppal.data.remote.data_source.register

import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel

interface RegisterRemoteDataSource {
    suspend fun registerUser(user: UserRegisterDataModel)
}