package eu.tutorials.myshoppal.data.local.data_source.main

import kotlinx.coroutines.flow.Flow

interface MainLocalDataSource {
    fun loadFromDisk(): Flow<String>
}