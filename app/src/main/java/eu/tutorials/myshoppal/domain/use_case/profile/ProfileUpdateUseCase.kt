package eu.tutorials.myshoppal.domain.use_case.profile

import android.net.Uri
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class ProfileUpdateUseCase @Inject constructor(
    private val profileUpdateUserUseCase: ProfileUpdateUserUseCase,
    private val profileUploadImageUseCase: ProfileUploadImageUseCase,
    private val profileSaveUserDiskUseCase: ProfileSaveUserDiskUseCase
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(selectedImageFileUri: Uri?, fileExtension: String?, id: String, userHashMap: HashMap<String, Any>): Flow<Unit> {
        if (selectedImageFileUri == Uri.EMPTY) {
            return profileUpdateUserUseCase(id, userHashMap)
                .flatMapConcat {
                    profileSaveUserDiskUseCase(userHashMap)
                }
        }
        return profileUploadImageUseCase(selectedImageFileUri, fileExtension)
            .flatMapConcat { url ->
                userHashMap[Constants.IMAGE] = url
                profileUpdateUserUseCase(id, userHashMap).collect()
                profileSaveUserDiskUseCase(userHashMap)
            }
    }
}