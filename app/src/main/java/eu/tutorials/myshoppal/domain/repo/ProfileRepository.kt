package eu.tutorials.myshoppal.domain.repo

import android.net.Uri
import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun loadFromDisk(): Flow<UserModel>
    fun updateUser(id: String, userHashMap: HashMap<String, Any>): Flow<Unit>
    fun uploadImage(imageFileUri: Uri?, fileExtension: String?): Flow<String>
}