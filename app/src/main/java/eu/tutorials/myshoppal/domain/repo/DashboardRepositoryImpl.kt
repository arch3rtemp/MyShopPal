package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.local.data_source.dashboard.DashboardLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val dashboardLocalDataSource: DashboardLocalDataSource
) : DashboardRepository {
    override fun loadFromDisk() = flow {
        emit(dashboardLocalDataSource.loadFromDisk().first())
    }
}