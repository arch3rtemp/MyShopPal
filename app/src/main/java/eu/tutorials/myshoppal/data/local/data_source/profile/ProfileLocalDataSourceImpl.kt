package eu.tutorials.myshoppal.data.local.data_source.profile

import eu.tutorials.myshoppal.data.local.data_store.DataStoreClient
import eu.tutorials.myshoppal.data.remote.util.toUserModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor(
    private val dataStoreClient: DataStoreClient
) : ProfileLocalDataSource {
    override fun loadFromDisk() = flow {
        emit(dataStoreClient.loadUserFromDataStore().first().toUserModel())
    }
}