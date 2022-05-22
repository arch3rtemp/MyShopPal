package eu.tutorials.myshoppal.data.remote.data_source.intro

import com.google.firebase.auth.FirebaseUser
import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import javax.inject.Inject

class IntroRemoteDataSourceImpl @Inject constructor(
    private val auth: AuthClient
) : IntroRemoteDataSource {
    override suspend fun retrieveUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }
}