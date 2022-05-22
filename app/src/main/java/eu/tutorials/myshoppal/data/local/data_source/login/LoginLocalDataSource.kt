package eu.tutorials.myshoppal.data.local.data_source.login

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LoginLocalDataSource {
    suspend fun saveToDisk(user: UserModel): Flow<Unit>
}