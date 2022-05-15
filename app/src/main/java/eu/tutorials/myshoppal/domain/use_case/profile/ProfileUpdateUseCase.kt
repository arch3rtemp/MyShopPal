package eu.tutorials.myshoppal.domain.use_case.profile

import android.net.Uri
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class ProfileUpdateUseCase @Inject constructor(
    private val profileUpdateUserUseCase: ProfileUpdateUserUseCase,
    private val profileUploadImageUseCase: ProfileUploadImageUseCase
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(selectedImageFileUri: Uri?, fileExtension: String?, id: String, userHashMap: HashMap<String, Any>): Flow<Unit> {
        return profileUploadImageUseCase(selectedImageFileUri, fileExtension)
            .flatMapConcat {
                userHashMap[Constants.IMAGE] = it
                profileUpdateUserUseCase(id, userHashMap)
            }
    }
}