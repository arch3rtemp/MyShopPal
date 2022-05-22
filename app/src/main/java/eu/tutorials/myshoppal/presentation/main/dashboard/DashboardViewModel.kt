package eu.tutorials.myshoppal.presentation.main.dashboard

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.repo.DashboardRepository
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : BaseViewModel<DashboardEvent, DashboardState, DashboardEffect>() {
    override fun createInitialState(): DashboardState {
        return DashboardState(viewState = ViewState.Idle)
    }

    init {
        setEvent(DashboardEvent.OnUserLoaded)
    }

    override fun handleEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.OnUserLoaded -> getUser()
            DashboardEvent.OnUserChecked -> { setState { copy(viewState = ViewState.Idle) } }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            dashboardRepository.loadFromDisk()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(it.message.toString()) }
                .collect {
                    setState { copy(viewState = ViewState.Success, user = it) }
                }
        }
    }

    private fun setStateError(message: String) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { DashboardEffect.Error(message) }
    }
}