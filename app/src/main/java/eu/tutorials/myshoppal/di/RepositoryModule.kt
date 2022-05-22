package eu.tutorials.myshoppal.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.tutorials.myshoppal.data.local.data_source.dashboard.DashboardLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.dashboard.DashboardLocalDataSourceImpl
import eu.tutorials.myshoppal.data.local.data_source.login.LoginLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.login.LoginLocalDataSourceImpl
import eu.tutorials.myshoppal.data.local.data_source.main.MainLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.main.MainLocalDataSourceImpl
import eu.tutorials.myshoppal.data.local.data_source.profile.ProfileLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.profile.ProfileLocalDataSourceImpl
import eu.tutorials.myshoppal.data.local.data_source.settings.SettingsLocalDataSource
import eu.tutorials.myshoppal.data.local.data_source.settings.SettingsLocalDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.intro.IntroRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.intro.IntroRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.login.LoginRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.profile.ProfileRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.profile.ProfileRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.recover.RecoverRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.recover.RecoverRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.register.RegisterRemoteDataSourceImpl
import eu.tutorials.myshoppal.data.remote.data_source.settings.SettingsRemoteDataSource
import eu.tutorials.myshoppal.data.remote.data_source.settings.SettingsRemoteDataSourceImpl
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
    abstract fun provideDashboardLocalDataSource(dashboardLocalDataSource: DashboardLocalDataSourceImpl): DashboardLocalDataSource

    @Binds
    @Singleton
    abstract fun provideIntroRemoteDataSource(introRemoteDataSource: IntroRemoteDataSourceImpl): IntroRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideSettingsRemoteDataSource(settingsRemoteDataSource: SettingsRemoteDataSourceImpl): SettingsRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideSettingsLocalDataSource(settingsLocalDataSource: SettingsLocalDataSourceImpl): SettingsLocalDataSource

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

    @Binds
    @Singleton
    abstract fun provideDashboardRepository(dashboardRepository: DashboardRepositoryImpl): DashboardRepository

    @Binds
    @Singleton
    abstract fun provideIntroRepository(introRepository: IntroRepositoryImpl): IntroRepository

    @Binds
    @Singleton
    abstract fun provideSettingsRepository(settingsRepository: SettingsRepositoryImpl): SettingsRepository

}