package eu.tutorials.myshoppal.domain.use_case.profile

import eu.tutorials.myshoppal.domain.repo.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileUpdateUserUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(id: String, userHashMap: HashMap<String, Any>): Flow<Unit> {
        return profileRepository.updateUser(id, userHashMap).flowOn(dispatcher)
    }
}