package eu.tutorials.myshoppal.domain.use_case.settings

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class SettingsLogoutUseCase @Inject constructor(
    private val settingsClearUserUseCase: SettingsClearUserUseCase,
    private val settingsSignOutUseCase: SettingsSignOutUseCase
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(): Flow<Unit> {
        return settingsSignOutUseCase()
            .flatMapConcat {
                settingsClearUserUseCase()
            }
    }
}