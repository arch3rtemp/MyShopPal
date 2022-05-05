package eu.tutorials.myshoppal.data.remote.firebase

import eu.tutorials.myshoppal.data.remote.model.UserDataModel

interface FirestoreClient {
    suspend fun saveUser(user: UserDataModel)
    suspend fun getUserDetails(uid: String): UserDataModel
}