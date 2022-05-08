package eu.tutorials.myshoppal.domain.repo

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun loadFromDisk(): Flow<String>
}