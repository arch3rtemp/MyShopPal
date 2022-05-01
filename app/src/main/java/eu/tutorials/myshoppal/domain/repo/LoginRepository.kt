package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginUser(user: UserLoginDataModel): Flow<Unit>
}