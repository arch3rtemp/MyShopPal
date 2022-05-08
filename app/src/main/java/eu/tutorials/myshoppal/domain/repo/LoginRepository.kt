package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun authUser(user: UserLoginModel): Flow<Unit>
    fun retrieveUser(): Flow<UserModel>
    fun saveToDisk(firstName: String, lastName: String): Flow<Unit>
}