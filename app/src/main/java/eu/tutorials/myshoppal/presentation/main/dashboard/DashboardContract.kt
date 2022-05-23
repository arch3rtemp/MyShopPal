package eu.tutorials.myshoppal.presentation.main.dashboard

import eu.tutorials.myshoppal.domain.model.UserModel
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

sealed class DashboardEvent : UiEvent {
    object OnUserLoaded : DashboardEvent()
    object OnUserChecked : DashboardEvent()
}

sealed class DashboardEffect : UiEffect {
    data class ShowSnackbar(val message: UiText, val status: String) : DashboardEffect()
}

data class DashboardState(val viewState: ViewState, val user: UserModel = UserModel.Empty) : UiState