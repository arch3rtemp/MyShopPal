package eu.tutorials.myshoppal.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreClientImpl @Inject constructor(
    private val context: Context
) : DataStoreClient {
    private val Context.dataStore by preferencesDataStore(name = Constants.MY_SHOP_PAL_PREFERENCES)

    override suspend fun saveUserToDataStore(firstName: String, lastName: String) {
        context.dataStore.edit {
            it[userKey] = "$firstName $lastName"
        }
    }

    override fun loadUserFromDataStore() = context.dataStore.data.map {
        it[userKey]
    }

    companion object {
        val userKey = stringPreferencesKey(Constants.LOGGED_IN_USERNAME)
    }
}