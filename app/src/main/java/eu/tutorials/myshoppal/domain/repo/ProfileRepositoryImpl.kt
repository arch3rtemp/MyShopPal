package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.local.data_source.profile.ProfileLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource
) : ProfileRepository {
    override fun loadFromDisk() = flow {
        emit(profileLocalDataSource.loadFromDisk().first())
    }
}