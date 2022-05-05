package eu.tutorials.myshoppal.data.remote.data_source.main

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClient

class MainRemoteDataSourceImpl(
    private val auth: AuthClient,
    private val firestore: FirestoreClient
) : MainRemoteDataSource {
    override suspend fun retrieveUser() {
        firestore.getUserDetails(auth.getCurrentUserId())
    }
}