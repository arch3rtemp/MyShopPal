package eu.tutorials.myshoppal.presentation.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.profile.LoadUserFromDiskUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loadUserFromDiskUseCase: LoadUserFromDiskUseCase
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    override fun createInitialState(): ProfileState {
        return ProfileState(viewState = ViewState.Idle)
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnLoadUser -> { loadUser() }
            ProfileEvent.OnUserEdited -> {}
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            loadUserFromDiskUseCase()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(it.message.toString()) }
                .collect {
                    setState { copy(viewState = ViewState.Success, user = it) }
                }
        }
    }

    private fun setStateError(message: String) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { ProfileEffect.Error(message) }
    }

    private fun setStateSuccess(message: String) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { ProfileEffect.Success(message) }
    }
}