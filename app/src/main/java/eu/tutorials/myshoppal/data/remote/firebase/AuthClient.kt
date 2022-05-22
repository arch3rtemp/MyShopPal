package eu.tutorials.myshoppal.data.remote.firebase

import com.google.firebase.auth.FirebaseUser
import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel

interface AuthClient {
    suspend fun createUser(user: UserRegisterDataModel, reduce: (UserDataModel) -> Unit)
    suspend fun signInUser(user: UserLoginDataModel)
    suspend fun signOutUser()
    suspend fun recoverPassword(email: String)
    suspend fun getCurrentUser(): FirebaseUser?
    fun getCurrentUserId(): String
}