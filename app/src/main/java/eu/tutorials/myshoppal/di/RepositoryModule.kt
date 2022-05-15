package eu.tutorials.myshoppal.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.tutorials.myshoppal.data.local.data_source.login.LoginLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.login.LoginLocalDataSourceImpl
import eu.tutorials.myshoppal.data.local.data_source.main.MainLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.main.MainLocalDataSourceImpl
import eu.tutorials.myshoppal.data.local.data_source.profile.ProfileLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.profile.ProfileLocalDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.profile.ProfileRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.profile.ProfileRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.recover.RecoverRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.recover.RecoverRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSourceImpl
import eu.tutorials.myshoppal.domain.repo.*
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
    abstract fun provideLoginLocalDataSource(loginLocalDataSourceImpl: LoginLocalDataSourceImpl): LoginLocalDataSource

    @Binds
    @Singleton
    abstract fun provideRecoverRemoteDataSource(recoverRemoteDataSourceImpl: RecoverRemoteDataSourceImpl): RecoverRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideProfileLocalDataSource(profileLocalDataSourceImpl: ProfileLocalDataSourceImpl): ProfileLocalDataSource

    @Binds
    @Singleton
    abstract fun provideProfileRemoteDataSource(profileRemoteDataSource: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideMainLocalDataSource(mainLocalDataSourceImpl: MainLocalDataSourceImpl): MainLocalDataSource

    @Binds
    @Singleton
    abstract fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    @Singleton
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun provideRecoverRepository(recoverRepositoryImpl: RecoverRepositoryImpl): RecoverRepository

    @Binds
    @Singleton
    abstract fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    abstract fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

}