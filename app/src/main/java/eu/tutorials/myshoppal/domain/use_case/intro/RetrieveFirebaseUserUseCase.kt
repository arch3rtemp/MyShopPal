package eu.tutorials.myshoppal.domain.use_case.intro

import com.google.firebase.auth.FirebaseUser
import eu.tutorials.myshoppal.domain.repo.IntroRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RetrieveFirebaseUserUseCase @Inject constructor(
    private val introRepository: IntroRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<FirebaseUser?> {
        return introRepository.retrieveUser().flowOn(dispatcher)
    }
}