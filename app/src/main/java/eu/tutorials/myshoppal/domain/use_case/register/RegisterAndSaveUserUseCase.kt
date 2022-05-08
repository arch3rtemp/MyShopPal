package eu.tutorials.myshoppal.domain.use_case.register

import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class RegisterAndSaveUserUseCase @Inject constructor(
    private val registerUserUseCase: CreateUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(user: UserRegisterModel): Flow<Unit> {
        return registerUserUseCase(user)
            .flatMapConcat {
                saveUserUseCase(it)
            }
    }
}