package eu.tutorials.myshoppal.data.remote.firebase

import android.net.Uri

interface StorageClient {
    suspend fun uploadImageToCloudStorage(imageFileUri: Uri?, fileExtension: String?): String

}