package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSource
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) : RegisterRepository {
    override fun registerUser(user: UserRegisterDataModel) = flow {
        emit(registerRemoteDataSource.registerUser(user))
    }
}