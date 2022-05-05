package eu.tutorials.myshoppal.data.remote.data_source.login

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import eu.tutorials.myshoppal.data.remote.util.toUserLoginDataModel
import eu.tutorials.myshoppal.domain.model.UserLoginModel
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val authClient: AuthClient
) : LoginRemoteDataSource {
    override suspend fun loginUser(user: UserLoginModel) {
        authClient.loginUser(user.toUserLoginDataModel())
    }
}