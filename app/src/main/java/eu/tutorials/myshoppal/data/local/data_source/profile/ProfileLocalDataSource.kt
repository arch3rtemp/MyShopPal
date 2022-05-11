package eu.tutorials.myshoppal.data.local.data_source.profile

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ProfileLocalDataSource {
    fun loadFromDisk(): Flow<UserModel>
}