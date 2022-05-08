package eu.tutorials.myshoppal.data.local.data_source.main

import eu.tutorials.myshoppal.data.local.data_store.DataStoreClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainLocalDataSourceImpl @Inject constructor(
    private val dataStoreClient: DataStoreClient
) : MainLocalDataSource {

    override fun loadFromDisk() = flow<String> {
        emit(dataStoreClient.loadUserFromDataStore().first() ?: "Jane Doe")
    }
}