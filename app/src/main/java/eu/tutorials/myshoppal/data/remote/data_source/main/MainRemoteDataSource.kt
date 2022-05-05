package eu.tutorials.myshoppal.data.remote.data_source.main

interface MainRemoteDataSource {
    suspend fun retrieveUser()
}