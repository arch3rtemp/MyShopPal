package eu.tutorials.myshoppal.data.remote.firebase

import eu.tutorials.myshoppal.data.remote.model.UserDataModel

interface FirestoreClient {
    suspend fun createUser(user: UserDataModel)
    suspend fun updateUser(id: String, userHashMap: HashMap<String, Any>)
    suspend fun getUserDetails(uid: String): UserDataModel
}