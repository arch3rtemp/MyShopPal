package eu.tutorials.myshoppal.data.remote.firebase

import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel

interface FirestoreClient {
    suspend fun registerUser(user: UserRegisterDataModel)
}