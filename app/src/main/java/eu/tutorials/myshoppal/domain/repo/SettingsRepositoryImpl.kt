package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.local.data_source.settings.SettingsLocalDataSource
import eu.tutorials.myshoppal.data.remote.data_source.settings.SettingsRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsRemoteDataSource: SettingsRemoteDataSource,
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {

    override fun loadUser() = flow {
        emit(settingsLocalDataSource.loadUser().first())
    }

    override fun signOutUser() = flow {
        emit(settingsRemoteDataSource.signOutUser())
    }

    override fun clearUser() = flow {
        emit(settingsLocalDataSource.clearUser().first())
    }
}