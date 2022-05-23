package eu.tutorials.myshoppal.presentation.main.recover

import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState
import eu.tutorials.myshoppal.presentation.base.UiText

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class RecoverEvent : UiEvent {
    data class OnRecover(val email: String) : RecoverEvent()
}

sealed class RecoverEffect : UiEffect {
    data class ShowSnackbar(val message: UiText, val status: String) : RecoverEffect()
    data class ShowToast(val message: UiText) : RecoverEffect()
}

data class RecoverState(val viewState: ViewState) : UiState