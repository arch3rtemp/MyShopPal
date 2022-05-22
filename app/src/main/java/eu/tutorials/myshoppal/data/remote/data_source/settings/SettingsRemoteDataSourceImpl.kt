package eu.tutorials.myshoppal.data.remote.data_source.settings

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import javax.inject.Inject

class SettingsRemoteDataSourceImpl @Inject constructor(
    private val auth: AuthClient
) : SettingsRemoteDataSource {
    override suspend fun signOutUser() {
        auth.signOutUser()
    }
}