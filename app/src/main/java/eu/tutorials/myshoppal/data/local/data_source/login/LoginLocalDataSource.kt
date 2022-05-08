package eu.tutorials.myshoppal.data.local.data_source.login

interface LoginLocalDataSource {
    suspend fun saveToDisk(firstName: String, lastName: String)
}