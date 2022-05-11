package eu.tutorials.myshoppal.presentation.login

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.use_case.login.LoginUseCase
import eu.tutorials.myshoppal.domain.use_case.login.SaveUserToDiskUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.utils.toUserLoginModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserToDiskUseCase: SaveUserToDiskUseCase
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    override fun createInitialState(): LoginState {
        return LoginState(viewState = ViewState.Idle)
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> {
                loginUser(event.loginUser)
            }
            is LoginEvent.OnLoggedIn -> {
                loggedIn()
            }
        }
    }

    private fun setStateError(message: String) {
        setState {
            copy(
                viewState = ViewState.Error
            )
        }
        setEffect { LoginEffect.Error(message) }
    }

    private fun setStateSuccess(message: String, user: UserModel) {
        setState { copy(viewState = ViewState.Success, user = user) }
        setEffect { LoginEffect.Success(message) }

    }

    private fun loginUser(loginUser: LoginUser) {
        viewModelScope.launch {
            if (validateLoginDetails(loginUser)) {
                loginUseCase(loginUser.toUserLoginModel())
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(it.message.toString()) }
                    .map {
                        setStateSuccess("Logged in successfully.", it)
                        saveUserToDiskUseCase(it)
                    }
                    .collect {
                        setState { copy(viewState = ViewState.Idle) }
                    }
            }
        }
    }

    private fun validateLoginDetails(loginUser: LoginUser): Boolean {
        loginUser.apply {
            return when {
                TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                    setStateError("Please enter an email id.")
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                    setStateError("Please enter a password.")
                    false
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun loggedIn() {
        setState { copy(viewState = ViewState.Idle) }
    }
}