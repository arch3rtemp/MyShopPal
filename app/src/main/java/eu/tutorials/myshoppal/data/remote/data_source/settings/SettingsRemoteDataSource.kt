package eu.tutorials.myshoppal.data.remote.data_source.settings

interface SettingsRemoteDataSource {
    suspend fun signOutUser()
}