package eu.tutorials.myshoppal.domain.use_case.login

import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val retrieveUserUseCase: RetrieveUserUseCase
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(user: UserLoginModel): Flow<UserModel> {
        return authUseCase(user)
            .flatMapConcat {
                retrieveUserUseCase()
            }
    }
}