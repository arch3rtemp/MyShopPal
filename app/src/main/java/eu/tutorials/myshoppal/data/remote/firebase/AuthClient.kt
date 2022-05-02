package eu.tutorials.myshoppal.data.remote.firebase

import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel

interface AuthClient {
    suspend fun createUser(user: UserRegisterDataModel, reduce: (UserRegisterDataModel) -> Unit)
    suspend fun loginUser(user: UserLoginDataModel)
    suspend fun recoverPassword(email: String)
}