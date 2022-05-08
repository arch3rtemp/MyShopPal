package eu.tutorials.myshoppal.domain.use_case.login

import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.repo.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(user: UserLoginModel): Flow<Unit> {
        return loginRepository.authUser(user).flowOn(dispatcher)
    }
}