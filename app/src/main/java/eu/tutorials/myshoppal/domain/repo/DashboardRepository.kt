package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun loadFromDisk(): Flow<UserModel>
}