package eu.tutorials.myshoppal.domain.repo

import android.net.Uri
import eu.tutorials.myshoppal.data.local.data_source.profile.ProfileLocalDataSource
import eu.tutorials.myshoppal.data.remote.data_source.profile.ProfileRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {
    override fun loadFromDisk() = flow {
        emit(profileLocalDataSource.loadFromDisk().first())
    }

    override fun updateUser(id: String, userHashMap: HashMap<String, Any>) = flow {
        emit(profileRemoteDataSource.updateUser(id, userHashMap))
    }

    override fun uploadImage(imageFileUri: Uri?, fileExtension: String?) = flow {
        emit(profileRemoteDataSource.uploadImage(imageFileUri, fileExtension))
    }
}