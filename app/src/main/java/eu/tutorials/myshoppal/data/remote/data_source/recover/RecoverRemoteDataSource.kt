package eu.tutorials.myshoppal.data.remote.data_source.recover

interface RecoverRemoteDataSource {
    suspend fun recoverPassword(email: String)
}