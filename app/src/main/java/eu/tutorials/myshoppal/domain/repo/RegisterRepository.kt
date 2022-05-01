package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun registerUser(user: UserRegisterDataModel): Flow<Unit>
}