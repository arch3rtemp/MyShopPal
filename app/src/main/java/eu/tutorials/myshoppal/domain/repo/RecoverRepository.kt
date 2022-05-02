package eu.tutorials.myshoppal.domain.repo

import kotlinx.coroutines.flow.Flow

interface RecoverRepository {
    fun recoverPassword(email: String): Flow<Unit>
}