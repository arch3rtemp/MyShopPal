package eu.tutorials.myshoppal.domain.use_case.register

import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class RegisterAndSaveUserUseCase @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) {
    operator fun invoke(user: UserRegisterModel): Flow<Unit> {
        return registerUserUseCase(user)
            .flatMapConcat {
                saveUserUseCase(it)
            }
    }
}