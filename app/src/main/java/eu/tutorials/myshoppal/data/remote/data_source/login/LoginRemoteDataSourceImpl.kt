package eu.tutorials.myshoppal.data.remote.data_source.login

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClient
import eu.tutorials.myshoppal.data.remote.util.toUserLoginDataModel
import eu.tutorials.myshoppal.data.remote.util.toUserModel
import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val auth: AuthClient,
    private val firestore: FirestoreClient
) : LoginRemoteDataSource {

    override suspend fun authUser(user: UserLoginModel) {
        auth.signInUser(user.toUserLoginDataModel())
    }

    override suspend fun retrieveUser(): UserModel {
        return firestore.getUserDetails(auth.getCurrentUserId()).toUserModel()
    }
}