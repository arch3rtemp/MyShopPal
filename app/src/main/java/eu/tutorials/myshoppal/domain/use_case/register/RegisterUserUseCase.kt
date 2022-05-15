package eu.tutorials.myshoppal.domain.use_case.register

import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val registerSaveUserUseCase: RegisterSaveUserUseCase
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(user: UserRegisterModel): Flow<Unit> {
        return createUserUseCase(user)
            .flatMapConcat {
                registerSaveUserUseCase(it)
            }
    }
}