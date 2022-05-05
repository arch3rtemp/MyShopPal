package eu.tutorials.myshoppal.data.remote.data_source.register

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel

interface RegisterRemoteDataSource {
    suspend fun registerUser(user: UserRegisterModel): UserModel
    suspend fun saveUser(user: UserModel)
}