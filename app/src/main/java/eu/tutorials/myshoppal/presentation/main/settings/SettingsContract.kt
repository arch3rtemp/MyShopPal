package eu.tutorials.myshoppal.presentation.main.settings

import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class SettingsEvent : UiEvent {
    object OnUserLoaded : SettingsEvent()
    object OnSignOut : SettingsEvent()
}

sealed class SettingsEffect: UiEffect {
    data class Success(val message: String) : SettingsEffect()
    data class Error(val message: String) : SettingsEffect()
    object Finish : SettingsEffect()
}

data class SettingsState(
    val viewState: ViewState,
    val user: UserModel = UserModel.Empty
) : UiState