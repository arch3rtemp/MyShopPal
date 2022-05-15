package eu.tutorials.myshoppal.data.remote.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.tasks.await

class StorageClientImpl(
    private val storage: FirebaseStorage
) : StorageClient {
    override suspend fun uploadImageToCloudStorage(imageFileUri: Uri?, fileExtension: String?): String {
        return storage.reference
            .child("${Constants.USER_PROFILE_IMAGE}${System.currentTimeMillis()}.$fileExtension")
            .putFile(imageFileUri!!)
            .await()
            .metadata
            ?.reference
            ?.downloadUrl
            ?.await()
            .toString()
    }
}