package eu.tutorials.myshoppal.domain.repo

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface IntroRepository {
    fun retrieveUser(): Flow<FirebaseUser?>
}