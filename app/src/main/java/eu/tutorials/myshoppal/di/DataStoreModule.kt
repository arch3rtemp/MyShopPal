package eu.tutorials.myshoppal.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.tutorials.myshoppal.data.local.data_store.DataStoreClient
import eu.tutorials.myshoppal.data.local.data_store.DataStoreClientImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStorePreferencesInstance(@ApplicationContext context: Context): DataStoreClient {
        return DataStoreClientImpl(context)
    }
}