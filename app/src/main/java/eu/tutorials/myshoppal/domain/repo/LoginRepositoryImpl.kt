package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.local.data_source.login.LoginLocalDataSource
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSource
import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun authUser(user: UserLoginModel) = flow {
        emit(loginRemoteDataSource.authUser(user))
    }

    override fun retrieveUser() = flow<UserModel> {
        emit(loginRemoteDataSource.retrieveUser())
    }

    override fun saveToDisk(firstName: String, lastName: String) = flow<Unit> {
        emit(loginLocalDataSource.saveToDisk(firstName, lastName))
    }
}