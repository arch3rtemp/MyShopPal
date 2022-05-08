package eu.tutorials.myshoppal.data.remote.firebase

import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel

interface AuthClient {
    suspend fun createUser(user: UserRegisterDataModel, reduce: (UserDataModel) -> Unit)
    suspend fun signInUser(user: UserLoginDataModel)
    suspend fun recoverPassword(email: String)
    fun getCurrentUserId(): String
}