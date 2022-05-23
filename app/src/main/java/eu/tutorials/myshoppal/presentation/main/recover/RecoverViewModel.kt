package eu.tutorials.myshoppal.presentation.main.recover

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.domain.use_case.recover.RecoverUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoverViewModel @Inject constructor(
    private val recoverUseCase: RecoverUseCase
) : BaseViewModel<RecoverEvent, RecoverState, RecoverEffect>() {

    override fun createInitialState(): RecoverState {
        return RecoverState(viewState = ViewState.Idle)
    }

    override fun handleEvent(event: RecoverEvent) {
        when (event) {
            is RecoverEvent.OnRecover -> { recoverPassword(event.email) }
        }
    }

    private fun setStateError(message: UiText) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { RecoverEffect.ShowSnackbar(message, Constants.STATUS_ERROR) }
    }

    private fun setStateSuccess(message: UiText) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { RecoverEffect.ShowToast(message) }
    }

    private fun recoverPassword(email: String) {
        viewModelScope.launch {
            if (validateRecoverDetails(email)) {
                recoverUseCase(email)
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                    .collect { setStateSuccess(UiText.StringResource(R.string.email_sent_successfully)) }
            }
        }
    }

    private fun validateRecoverDetails(email: String): Boolean {
        return when {
            TextUtils.isEmpty(email.trim { it <= ' '}) -> {
                setStateError(UiText.StringResource(R.string.error_enter_email))
                false
            }
            else -> { true }
        }
    }
}