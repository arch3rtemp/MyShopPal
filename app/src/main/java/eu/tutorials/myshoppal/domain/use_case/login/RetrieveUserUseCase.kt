package eu.tutorials.myshoppal.domain.use_case.login

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.repo.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RetrieveUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<UserModel> {
        return loginRepository.retrieveUser().flowOn(dispatcher)
    }
}