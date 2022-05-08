package eu.tutorials.myshoppal.data.remote.data_source.register

import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClient
import eu.tutorials.myshoppal.data.remote.util.toUserDataModel
import eu.tutorials.myshoppal.data.remote.util.toUserModel
import eu.tutorials.myshoppal.data.remote.util.toUserRegisterDataModel
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import javax.inject.Inject

class RegisterRemoteDataSourceImpl @Inject constructor(
    private val authClient: AuthClient,
    private val firestore: FirestoreClient
) : RegisterRemoteDataSource {
    override suspend fun createUser(user: UserRegisterModel): UserModel {
        var userModel = UserModel()
        authClient.createUser(user.toUserRegisterDataModel()) {
            userModel = it.toUserModel()
        }
        return userModel
    }

    override suspend fun saveUser(user: UserModel) {
        firestore.saveUser(user.toUserDataModel())
    }
}