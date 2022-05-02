package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.data_source.recover.RecoverRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecoverRepositoryImpl @Inject constructor(
    private val recoverRemoteDataSource: RecoverRemoteDataSource
) : RecoverRepository {
    override fun recoverPassword(email: String) = flow {
        emit(recoverRemoteDataSource.recoverPassword(email))
    }
}