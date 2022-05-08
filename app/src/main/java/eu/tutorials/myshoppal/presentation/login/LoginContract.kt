package eu.tutorials.myshoppal.presentation.login

import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState
import eu.tutorials.myshoppal.presentation.model.LoginUser

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class LoginEvent : UiEvent {
    data class OnLogin(val loginUser: LoginUser) : LoginEvent()
    object OnLoggedIn : LoginEvent()
}

sealed class LoginEffect : UiEffect {
    data class Success(val message: String) : LoginEffect()
    data class Error(val message: String) : LoginEffect()
}

data class LoginState(val viewState: ViewState) : UiState