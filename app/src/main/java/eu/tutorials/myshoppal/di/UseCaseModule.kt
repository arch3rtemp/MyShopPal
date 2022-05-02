package eu.tutorials.myshoppal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import eu.tutorials.myshoppal.domain.repo.LoginRepository
import eu.tutorials.myshoppal.domain.repo.RecoverRepository
import eu.tutorials.myshoppal.domain.repo.RegisterRepository
import eu.tutorials.myshoppal.domain.use_case.register.LoginUseCase
import eu.tutorials.myshoppal.domain.use_case.register.RecoverUseCase
import eu.tutorials.myshoppal.domain.use_case.register.RegisterUseCase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideRegisterUseCase(registerRepository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(registerRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideRecoverUseCase(recoverRepository: RecoverRepository): RecoverUseCase {
        return RecoverUseCase(recoverRepository, Dispatchers.IO)
    }
}