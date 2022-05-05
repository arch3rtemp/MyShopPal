package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.domain.model.UserLoginModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginUser(user: UserLoginModel): Flow<Unit>
}