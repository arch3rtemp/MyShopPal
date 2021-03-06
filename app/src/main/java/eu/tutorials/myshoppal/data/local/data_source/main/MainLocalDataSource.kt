package eu.tutorials.myshoppal.data.local.data_source.main

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface MainLocalDataSource {
    fun loadFromDisk(): Flow<UserModel>
}