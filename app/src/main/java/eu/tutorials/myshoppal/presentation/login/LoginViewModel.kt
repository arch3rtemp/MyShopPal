package eu.tutorials.myshoppal.presentation.login

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.register.LoginUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.utils.toUserAuthModel
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
        }
    }

    private fun setStateError(message: String) {
        setState { copy(
            viewState = ViewState.Error
        ) }
        setEffect { LoginEffect.Error(message) }
    }

    private fun setStateSuccess(message: String) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { LoginEffect.Success(message) }
    }

    private fun loginUser(loginUser: LoginUser) {
        viewModelScope.launch {
            if (validateLoginDetails(loginUser)) {
                loginUseCase(loginUser.toUserAuthModel())
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(it.message.toString()) }
                    .collect { setStateSuccess("Logged in successfully.") }
            }
        }
    }

    private fun validateLoginDetails(loginUser: LoginUser): Boolean {
        loginUser.apply {
            return when {
                TextUtils.isEmpty(email.trim { it <= ' '}) -> {
                    setStateError("Please enter an email id.")
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' '}) -> {
                    setStateError("Please enter a password.")
                    false
                }
                else -> {
                    true
                }
            }
        }
    }
}