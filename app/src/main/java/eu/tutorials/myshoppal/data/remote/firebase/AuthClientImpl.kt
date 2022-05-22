package eu.tutorials.myshoppal.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import eu.tutorials.myshoppal.data.remote.util.toUserDataModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthClientImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthClient {

    override suspend fun createUser(
        user: UserRegisterDataModel,
        reduce: (UserDataModel) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                val firebaseUser: FirebaseUser = result.user!!
                val userId = firebaseUser.uid
                reduce(user.toUserDataModel(userId))
                auth.signOut()
            }
            .await()
    }

    override suspend fun signInUser(user: UserLoginDataModel) {
        auth.signInWithEmailAndPassword(user.email, user.password)
            .await()
    }

    override suspend fun signOutUser() {
        auth.signOut()
    }

    override suspend fun recoverPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .await()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override fun getCurrentUserId(): String {
        return auth.currentUser?.uid ?: ""
    }
}