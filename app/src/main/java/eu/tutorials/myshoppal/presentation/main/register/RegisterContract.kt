package eu.tutorials.myshoppal.presentation.main.register

import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.presentation.model.RegisterUser

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class RegisterEvent : UiEvent {
    data class OnRegister(val registerUser: RegisterUser) : RegisterEvent()
}

sealed class RegisterEffect : UiEffect {
    data class ShowSnackbar(val message: UiText, val status: String) : RegisterEffect()
    data class ShowToast(val message: UiText) : RegisterEffect()
}

data class RegisterState(val viewState: ViewState) : UiState