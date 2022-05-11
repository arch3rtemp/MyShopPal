package eu.tutorials.myshoppal.domain.repo

import eu.tutorials.myshoppal.data.local.data_source.main.MainLocalDataSource
import eu.tutorials.myshoppal.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainLocalDataSource: MainLocalDataSource
) : MainRepository {

    override fun loadFromDisk(): Flow<UserModel> = flow {
        emit(mainLocalDataSource.loadFromDisk().first())
    }
}