package eu.tutorials.myshoppal.domain.use_case.main

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.repo.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainLoadUserDiskUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<UserModel> {
        return mainRepository.loadFromDisk().flowOn(dispatcher)
    }
}