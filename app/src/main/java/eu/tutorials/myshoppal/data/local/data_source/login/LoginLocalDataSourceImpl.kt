package eu.tutorials.myshoppal.data.local.data_source.login

import eu.tutorials.myshoppal.data.local.data_store.DataStoreClient
import eu.tutorials.myshoppal.data.remote.util.toUserDataModel
import eu.tutorials.myshoppal.domain.model.UserModel
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val dataStoreClient: DataStoreClient
) : LoginLocalDataSource {

    override suspend fun saveToDisk(user: UserModel) {
        dataStoreClient.saveUserToDataStore(user.toUserDataModel())
    }
}