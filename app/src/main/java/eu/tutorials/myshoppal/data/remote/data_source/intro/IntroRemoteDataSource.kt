package eu.tutorials.myshoppal.data.remote.data_source.intro

import com.google.firebase.auth.FirebaseUser

interface IntroRemoteDataSource {
    suspend fun retrieveUser(): FirebaseUser?
}