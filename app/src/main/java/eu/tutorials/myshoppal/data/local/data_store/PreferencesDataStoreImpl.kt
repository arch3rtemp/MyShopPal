package eu.tutorials.myshoppal.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import eu.tutorials.myshoppal.utils.Constants
import javax.inject.Inject

class PreferencesDataStoreImpl @Inject constructor(
    private val context: Context
) : PreferencesDataStore {
    private val Context.dataStore by preferencesDataStore(name = Constants.MY_SHOP_PAL_PREFERENCES)
}