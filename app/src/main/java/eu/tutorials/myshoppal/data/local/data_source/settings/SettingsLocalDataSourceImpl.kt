package eu.tutorials.myshoppal.data.local.data_source.settings

import eu.tutorials.myshoppal.data.local.data_store.DataStoreClient
import eu.tutorials.myshoppal.data.remote.util.toUserModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsLocalDataSourceImpl @Inject constructor(
    private val dataStoreClient: DataStoreClient
) : SettingsLocalDataSource {

    override fun loadUser() = flow {
        emit(dataStoreClient.loadUserFromDataStore().first().toUserModel())
    }

    override fun clearUser() = flow {
        emit(dataStoreClient.clearUser())
    }
}