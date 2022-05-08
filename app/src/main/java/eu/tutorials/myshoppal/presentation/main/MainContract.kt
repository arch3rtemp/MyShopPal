package eu.tutorials.myshoppal.presentation.main

import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState


sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class MainEvent : UiEvent {
    object OnUserLoaded : MainEvent()
}

sealed class MainEffect : UiEffect {
    data class Success(val message: String) : MainEffect()
    data class Error(val message: String) : MainEffect()
}

data class MainState(
    val viewState: ViewState,
    val name: String = "John Doe"
) : UiState