package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun loadUser(): Flow<UserModel>
    fun signOutUser(): Flow<Unit>
    fun clearUser(): Flow<Unit>
}