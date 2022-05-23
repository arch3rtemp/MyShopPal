package eu.tutorials.myshoppal.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreClientImpl @Inject constructor(
    private val context: Context
) : DataStoreClient {
    private val Context.dataStore by preferencesDataStore(name = Constants.MY_SHOP_PAL_PREFERENCES)

    override suspend fun saveUserToDataStore(user: UserDataModel) {
        context.dataStore.edit {
            it[userId] = user.id
            it[userFirstName] = user.firstName
            it[userLastName] = user.lastName
            it[userEmail] = user.email
            it[userImage] = user.image
            it[userMobile] = user.mobile
            it[userSex] = user.sex
            it[userProfileCompleted] = user.profileCompleted
        }
    }

    override suspend fun updateUserToDataStore(userHashMap: HashMap<String, Any>) {
        context.dataStore.edit {
            val firstName = userHashMap[Constants.FIRST_NAME]
            if (firstName is String) {
                it[userFirstName] = firstName
            }
            val lastName = userHashMap[Constants.LAST_NAME]
            if (lastName is String) {
                it[userLastName] = lastName
            }
            val mobile = userHashMap[Constants.MOBILE]
            if (mobile is Long) {
                it[userMobile] = mobile
            }
            val sex = userHashMap[Constants.SEX]
            if (sex is String) {
                it[userSex] = sex
            }
            val profileCompleted = userHashMap[Constants.PROFILE_COMPLETED]
            if (profileCompleted is Int) {
                it[userProfileCompleted] = profileCompleted
            }
            val imageUrl = userHashMap[Constants.IMAGE]
            if (imageUrl is String) {
                it[userImage] = imageUrl
            }
        }
    }

    override fun loadUserFromDataStore() = context.dataStore.data.map {
        UserDataModel(
            it[userId] ?: "",
            it[userFirstName] ?: "",
            it[userLastName] ?: "",
            it[userEmail] ?: "",
            it[userImage] ?: "",
            it[userMobile] ?: 0L,
            it[userSex] ?: "",
            it[userProfileCompleted] ?: 0
        )
    }

    override suspend fun clearUser() {
        context.dataStore.edit {
            it.clear()
        }
    }

    companion object {
        val userId = stringPreferencesKey(Constants.USER_ID)
        val userFirstName = stringPreferencesKey(Constants.FIRST_NAME)
        val userLastName = stringPreferencesKey(Constants.LAST_NAME)
        val userEmail = stringPreferencesKey(Constants.EMAIL)
        val userImage = stringPreferencesKey(Constants.IMAGE)
        val userMobile = longPreferencesKey(Constants.MOBILE)
        val userSex = stringPreferencesKey(Constants.SEX)
        val userProfileCompleted = intPreferencesKey(Constants.PROFILE_COMPLETED)
    }
}