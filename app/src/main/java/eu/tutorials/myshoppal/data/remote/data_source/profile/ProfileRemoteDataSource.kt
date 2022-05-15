package eu.tutorials.myshoppal.data.remote.data_source.profile

import android.net.Uri

interface ProfileRemoteDataSource {
    suspend fun updateUser(id: String, userHashMap: HashMap<String, Any>)
    suspend fun uploadImage(imageFileUri: Uri?, fileExtension: String?): String
}