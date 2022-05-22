package eu.tutorials.myshoppal.domain.use_case.profile

import eu.tutorials.myshoppal.domain.repo.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileSaveUserDiskUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(userHashMap: HashMap<String, Any>): Flow<Unit> {
        return profileRepository.updateToDisk(userHashMap).flowOn(dispatcher)
    }
}