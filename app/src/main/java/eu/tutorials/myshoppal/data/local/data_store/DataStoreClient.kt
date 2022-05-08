package eu.tutorials.myshoppal.data.local.data_store

import kotlinx.coroutines.flow.Flow

interface DataStoreClient {
    suspend fun saveUserToDataStore(firstName: String, lastName: String)
    fun loadUserFromDataStore(): Flow<String?>
}