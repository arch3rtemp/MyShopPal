package eu.tutorials.myshoppal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import eu.tutorials.myshoppal.domain.repo.*
import eu.tutorials.myshoppal.domain.use_case.login.AuthUseCase
import eu.tutorials.myshoppal.domain.use_case.login.RetrieveUserUseCase
import eu.tutorials.myshoppal.domain.use_case.login.SaveUserDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.main.MainLoadUserDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileLoadUserDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileUpdateUserUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileUploadImageUseCase
import eu.tutorials.myshoppal.domain.use_case.recover.RecoverUseCase
import eu.tutorials.myshoppal.domain.use_case.register.CreateUserUseCase
import eu.tutorials.myshoppal.domain.use_case.register.RegisterSaveUserUseCase
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
    fun provideRegisterSaveUserUseCase(registerRepository: RegisterRepository): RegisterSaveUserUseCase {
        return RegisterSaveUserUseCase(registerRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthUseCase(loginRepository: LoginRepository): AuthUseCase {
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
    fun provideSaveUserDiskUseCase(loginRepository: LoginRepository): SaveUserDiskUseCase {
        return SaveUserDiskUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideMainLoadUserDiskUseCase(mainRepository: MainRepository): MainLoadUserDiskUseCase {
        return MainLoadUserDiskUseCase(mainRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideProfileLoadUserDiskUseCase(profileRepository: ProfileRepository): ProfileLoadUserDiskUseCase {
        return ProfileLoadUserDiskUseCase(profileRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideProfileSaveUserUseCase(profileRepository: ProfileRepository): ProfileUpdateUserUseCase {
        return ProfileUpdateUserUseCase(profileRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideProfileUploadImageUseCase(profileRepository: ProfileRepository): ProfileUploadImageUseCase {
        return ProfileUploadImageUseCase(profileRepository, Dispatchers.IO)
    }
}