package eu.tutorials.myshoppal.domain.use_case.profile

import android.net.Uri
import eu.tutorials.myshoppal.domain.repo.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileUploadImageUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(imageFileUri: Uri?, fileExtension: String?): Flow<String> {
        return profileRepository.uploadImage(imageFileUri, fileExtension).flowOn(dispatcher)
    }
}