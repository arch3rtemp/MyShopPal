package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun createUser(user: UserRegisterModel): Flow<UserModel>
    fun saveUser(user: UserModel): Flow<Unit>
}