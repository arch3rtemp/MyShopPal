package eu.tutorials.myshoppal.presentation.main.intro

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.intro.RetrieveFirebaseUserUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val retrieveFirebaseUserUseCase: RetrieveFirebaseUserUseCase
) : BaseViewModel<IntroEvent, IntroState, IntroEffect>() {
    override fun createInitialState(): IntroState {
        return IntroState(viewState = ViewState.Idle, null)
    }

    override fun handleEvent(event: IntroEvent) {
        when (event) {
            IntroEvent.OnUserLoaded -> { loadUser() }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            retrieveFirebaseUserUseCase()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                .collect {
                    setState { copy(viewState = ViewState.Success, user = it) }
                }
        }
    }

    private fun setStateError(message: UiText) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { IntroEffect.ShowSnackbar(message, Constants.STATUS_ERROR) }
    }
}