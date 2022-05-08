package eu.tutorials.myshoppal.domain.use_case.login

import eu.tutorials.myshoppal.domain.repo.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SaveUserToDiskUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(firstName: String, lastName: String): Flow<Unit> {
        return loginRepository.saveToDisk(firstName, lastName).flowOn(dispatcher)
    }
}