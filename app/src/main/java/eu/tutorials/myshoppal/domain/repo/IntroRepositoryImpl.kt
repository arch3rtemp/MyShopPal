package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.remote.data_source.intro.IntroRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
    private val introRemoteDataSource: IntroRemoteDataSource
) : IntroRepository {
    override fun retrieveUser() = flow {
        emit(introRemoteDataSource.retrieveUser())
    }
}