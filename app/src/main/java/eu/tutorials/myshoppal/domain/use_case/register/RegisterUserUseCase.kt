package eu.tutorials.myshoppal.domain.use_case.register

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import eu.tutorials.myshoppal.domain.repo.RegisterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(user: UserRegisterModel): Flow<UserModel> {
        return registerRepository.registerUser(user).flowOn(dispatcher)
    }
}