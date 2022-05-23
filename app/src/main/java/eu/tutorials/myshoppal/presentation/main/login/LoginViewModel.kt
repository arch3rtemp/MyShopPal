package eu.tutorials.myshoppal.presentation.main.login

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.domain.use_case.login.LoginUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.utils.Constants
import eu.tutorials.myshoppal.utils.toUserLoginModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
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

    private fun setStateError(message: UiText) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { LoginEffect.ShowSnackbar(message, Constants.STATUS_ERROR) }
    }

    private fun loginUser(loginUser: LoginUser) {
        viewModelScope.launch {
            if (validateLoginDetails(loginUser)) {
                loginUseCase(loginUser.toUserLoginModel())
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch {
                        setStateError(UiText.DynamicString(it.message.toString())) }
                    .collect {
                        setState { copy(viewState = ViewState.Success, user = user) }
                        setState { copy(viewState = ViewState.Idle) }
                        setEffect { LoginEffect.Finish }
                    }
            }
        }
    }

    private fun validateLoginDetails(loginUser: LoginUser): Boolean {
        loginUser.apply {
            return when {
                TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_email))
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_password))
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