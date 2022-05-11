package eu.tutorials.myshoppal.data.local.data_store

import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface DataStoreClient {
    suspend fun saveUserToDataStore(user: UserDataModel)
    fun loadUserFromDataStore(): Flow<UserDataModel>
}