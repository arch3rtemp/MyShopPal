package eu.tutorials.myshoppal.data.remote.data_source.profile

import android.net.Uri
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClient
import eu.tutorials.myshoppal.data.remote.firebase.StorageClient
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirestoreClient,
    private val storage: StorageClient
) : ProfileRemoteDataSource {
    override suspend fun updateUser(id: String, userHashMap: HashMap<String, Any>) {
        firestore.updateUser(id, userHashMap)
    }

    override suspend fun uploadImage(imageFileUri: Uri?, fileExtension: String?): String {
        return storage.uploadImageToCloudStorage(imageFileUri, fileExtension)
    }
}