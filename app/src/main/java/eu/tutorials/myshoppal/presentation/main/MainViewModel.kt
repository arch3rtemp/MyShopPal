package eu.tutorials.myshoppal.presentation.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.main.LoadUserFromDiskUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadUserFromDiskUseCase: LoadUserFromDiskUseCase
) : BaseViewModel<MainEvent, MainState, MainEffect>() {

    override fun createInitialState(): MainState {
        return MainState(viewState = ViewState.Idle)
    }

    override fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.OnUserLoaded -> { setUserName() }
        }
    }

    private fun setStateError(message: String) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { MainEffect.Error(message) }
    }

    private fun setStateSuccess(message: String) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { MainEffect.Success(message) }
    }

    private fun setUserName() {
        viewModelScope.launch {
            loadUserFromDiskUseCase()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(it.message.toString()) }
                .collect { setState { copy(viewState = ViewState.Success, user = it) } }
        }
    }
}