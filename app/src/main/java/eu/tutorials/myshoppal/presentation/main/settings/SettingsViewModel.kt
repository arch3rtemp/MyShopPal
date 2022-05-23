package eu.tutorials.myshoppal.presentation.main.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.settings.SettingsLoadUserDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.settings.SettingsLogoutUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsLoadUserDiskUseCase: SettingsLoadUserDiskUseCase,
    private val settingsLogoutUseCase: SettingsLogoutUseCase
) : BaseViewModel<SettingsEvent, SettingsState, SettingsEffect>() {

    override fun createInitialState(): SettingsState {
        return SettingsState(viewState = ViewState.Idle)
    }

    override fun handleEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.OnUserLoaded -> { loadUser() }
            SettingsEvent.OnSignOut -> { logoutUser() }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            settingsLoadUserDiskUseCase()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                .collect {
                    setState { copy(viewState = ViewState.Success, user = it) }
                    setState { copy(viewState = ViewState.Idle) }
                }
        }
    }

    private fun logoutUser() {
        viewModelScope.launch {
            settingsLogoutUseCase()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                .collect {
                    setState { copy(viewState = ViewState.Success) }
                    setEffect { SettingsEffect.Finish }
                }
        }
    }

    private fun setStateError(message: UiText) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { SettingsEffect.ShowSnackbar(message, Constants.STATUS_ERROR) }
    }
}