package eu.tutorials.myshoppal.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthClientImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthClient {

    override suspend fun createUser(
        user: UserRegisterDataModel,
        reduce: (UserRegisterDataModel) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                val firebaseUser: FirebaseUser = result.user!!
                val userId = firebaseUser.uid
                auth.signOut()
                reduce(user.copy(id = userId))
            }
            .await()
    }

    override suspend fun loginUser(user: UserLoginDataModel) {
        auth.signInWithEmailAndPassword(user.email, user.password)
            .await()
    }

    override suspend fun recoverPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .await()
    }
}