package eu.tutorials.myshoppal.presentation.main.intro

import com.google.firebase.auth.FirebaseUser
import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class IntroEvent : UiEvent {
    object OnUserLoaded: IntroEvent()
}

sealed class IntroEffect : UiEffect {
    data class Success(val message: String) : IntroEffect()
    data class Error(val message: String) : IntroEffect()
}

data class IntroState(val viewState: ViewState, val user: FirebaseUser?) : UiState