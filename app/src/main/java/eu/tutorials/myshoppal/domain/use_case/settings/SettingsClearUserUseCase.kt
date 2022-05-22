package eu.tutorials.myshoppal.domain.use_case.settings

import eu.tutorials.myshoppal.domain.repo.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingsClearUserUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<Unit> {
        return settingsRepository.clearUser().flowOn(dispatcher)
    }
}