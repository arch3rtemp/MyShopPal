package eu.tutorials.myshoppal.presentation.recover

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.recover.RecoverUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
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

    private fun setStateError(message: String) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { RecoverEffect.Error(message) }
    }

    private fun setStateSuccess(message: String) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { RecoverEffect.Success(message) }
    }

    private fun recoverPassword(email: String) {
        viewModelScope.launch {
            if (validateRecoverDetails(email)) {
                recoverUseCase(email)
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(it.message.toString()) }
                    .collect { setStateSuccess("Email sent successfully to reset your password!") }
            }
        }
    }

    private fun validateRecoverDetails(email: String): Boolean {
        return when {
            TextUtils.isEmpty(email.trim { it <= ' '}) -> {
                setStateError("Please enter your email address.")
                false
            }
            else -> { true }
        }
    }
}