package eu.tutorials.myshoppal.data.local.data_source.login

import eu.tutorials.myshoppal.domain.model.UserModel

interface LoginLocalDataSource {
    suspend fun saveToDisk(user: UserModel)
}