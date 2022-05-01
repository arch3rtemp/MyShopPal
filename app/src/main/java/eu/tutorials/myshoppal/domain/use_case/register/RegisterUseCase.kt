package eu.tutorials.myshoppal.domain.use_case.register

import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import eu.tutorials.myshoppal.domain.repo.RegisterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(user: UserRegisterDataModel): Flow<Unit> {
        return registerRepository.registerUser(user).flowOn(dispatcher)
    }
}