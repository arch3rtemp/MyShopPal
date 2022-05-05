package eu.tutorials.myshoppal.data.local.data_source.main

import eu.tutorials.myshoppal.data.local.data_store.PreferencesDataStore
import javax.inject.Inject

class MainLocalDataSourceImpl @Inject constructor(
    private val dataStore: PreferencesDataStore
) : MainLocalDataSource {
}