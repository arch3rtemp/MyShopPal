package eu.tutorials.myshoppal.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreClientImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreClient {

    override suspend fun saveUser(user: UserDataModel) {

        firestore.collection(Constants.USERS)
            .document(user.id)
            .set(user, SetOptions.merge())
            .await()
    }

    override suspend fun getUserDetails(uid: String): UserDataModel {
        return firestore.collection(Constants.USERS)
            .document(uid)
            .get()
            .await()
            .toObject(UserDataModel::class.java)!!
    }
}