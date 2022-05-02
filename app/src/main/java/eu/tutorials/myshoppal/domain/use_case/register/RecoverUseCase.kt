package eu.tutorials.myshoppal.domain.use_case.register

import eu.tutorials.myshoppal.domain.repo.RecoverRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecoverUseCase @Inject constructor(
    private val recoverRepository: RecoverRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(email: String): Flow<Unit> {
        return recoverRepository.recoverPassword(email).flowOn(dispatcher)
    }
}