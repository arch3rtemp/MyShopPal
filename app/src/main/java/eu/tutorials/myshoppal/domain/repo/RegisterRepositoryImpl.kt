package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSource
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) : RegisterRepository {
    override fun createUser(user: UserRegisterModel) = flow {
        emit(registerRemoteDataSource.createUser(user))
    }

    override fun saveUser(user: UserModel) = flow {
        emit(registerRemoteDataSource.saveUser(user))
    }
}