package eu.tutorials.myshoppal.data.local.data_source.login

import eu.tutorials.myshoppal.data.local.data_store.DataStoreClient
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val dataStoreClient: DataStoreClient
) : LoginLocalDataSource {

    override suspend fun saveToDisk(firstName: String, lastName: String) {
        dataStoreClient.saveUserToDataStore(firstName, lastName)
    }
}