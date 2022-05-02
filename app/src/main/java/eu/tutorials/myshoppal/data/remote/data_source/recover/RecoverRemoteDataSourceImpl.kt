package eu.tutorials.myshoppal.data.remote.data_source.recover

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import javax.inject.Inject

class RecoverRemoteDataSourceImpl @Inject constructor(
    private val authClient: AuthClient
) : RecoverRemoteDataSource {
    override suspend fun recoverPassword(email: String) {
        authClient.recoverPassword(email)
    }
}