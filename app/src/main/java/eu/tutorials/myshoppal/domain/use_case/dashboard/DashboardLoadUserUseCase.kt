package eu.tutorials.myshoppal.domain.use_case.dashboard

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.repo.DashboardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardLoadUserUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<UserModel> {
        return dashboardRepository.loadFromDisk().flowOn(dispatcher)
    }
}