package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSource
import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {
    override fun loginUser(user: UserLoginDataModel) = flow {
        emit(loginRemoteDataSource.loginUser(user))
    }
}