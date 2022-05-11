package eu.tutorials.myshoppal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import eu.tutorials.myshoppal.domain.repo.*
import eu.tutorials.myshoppal.domain.use_case.login.AuthUseCase
import eu.tutorials.myshoppal.domain.use_case.login.RetrieveUserUseCase
import eu.tutorials.myshoppal.domain.use_case.login.SaveUserToDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.LoadUserFromDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.recover.RecoverUseCase
import eu.tutorials.myshoppal.domain.use_case.register.CreateUserUseCase
import eu.tutorials.myshoppal.domain.use_case.register.SaveUserUseCase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideRegisterUserUseCase(registerRepository: RegisterRepository): CreateUserUseCase {
        return CreateUserUseCase(registerRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideSaveUserUseCase(registerRepository: RegisterRepository): SaveUserUseCase {
        return SaveUserUseCase(registerRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(loginRepository: LoginRepository): AuthUseCase {
        return AuthUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideRecoverUseCase(recoverRepository: RecoverRepository): RecoverUseCase {
        return RecoverUseCase(recoverRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideRetrieveUserUseCase(loginRepository: LoginRepository): RetrieveUserUseCase {
        return RetrieveUserUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideSaveUserToDiskUseCase(loginRepository: LoginRepository): SaveUserToDiskUseCase {
        return SaveUserToDiskUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadUserFromDiskMainUseCase(mainRepository: MainRepository): eu.tutorials.myshoppal.domain.use_case.main.LoadUserFromDiskUseCase {
        return eu.tutorials.myshoppal.domain.use_case.main.LoadUserFromDiskUseCase(mainRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadUserFromDiskUseCase(profileRepository: ProfileRepository): LoadUserFromDiskUseCase {
        return LoadUserFromDiskUseCase(profileRepository, Dispatchers.IO)
    }
}