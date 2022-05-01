package eu.tutorials.myshoppal.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSourceImpl
import eu.tutorials.myshoppal.domain.repo.LoginRepository
import eu.tutorials.myshoppal.domain.repo.LoginRepositoryImpl
import eu.tutorials.myshoppal.domain.repo.RegisterRepository
import eu.tutorials.myshoppal.domain.repo.RegisterRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRegisterRemoteDataSource(registerRemoteDataSourceImpl: RegisterRemoteDataSourceImpl): RegisterRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideLoginRemoteDataSource(loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl): LoginRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    @Singleton
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}