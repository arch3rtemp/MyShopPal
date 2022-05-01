package eu.tutorials.myshoppal.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.tutorials.myshoppal.data.remote.firebase.AuthClient
import eu.tutorials.myshoppal.data.remote.firebase.AuthClientImpl
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClient
import eu.tutorials.myshoppal.data.remote.firebase.FirestoreClientImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthClient(firebaseAuth: FirebaseAuth): AuthClient {
        return AuthClientImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirestoreClient(firestore: FirebaseFirestore): FirestoreClient {
        return FirestoreClientImpl(firestore)
    }
}