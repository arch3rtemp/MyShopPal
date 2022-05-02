package eu.tutorials.myshoppal.data.remote.data_source.register

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClient
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterRemoteDataSourceImpl @Inject constructor(
    private val authClient: AuthClient,
    private val firestore: FirestoreClient
) : RegisterRemoteDataSource {
    override suspend fun registerUser(user: UserRegisterDataModel) {
        authClient.createUser(user) {
            CoroutineScope(Dispatchers.IO).launch {
                firestore.registerUser(it)
            }
        }
    }
}