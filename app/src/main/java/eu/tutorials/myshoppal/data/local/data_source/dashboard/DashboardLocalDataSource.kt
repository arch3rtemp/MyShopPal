package eu.tutorials.myshoppal.data.local.data_source.dashboard

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface DashboardLocalDataSource {
    fun loadFromDisk(): Flow<UserModel>
}