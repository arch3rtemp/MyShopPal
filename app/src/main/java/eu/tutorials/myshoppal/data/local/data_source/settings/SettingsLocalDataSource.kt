package eu.tutorials.myshoppal.data.local.data_source.settings

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {
    fun loadUser(): Flow<UserModel>
    fun clearUser(): Flow<Unit>
}