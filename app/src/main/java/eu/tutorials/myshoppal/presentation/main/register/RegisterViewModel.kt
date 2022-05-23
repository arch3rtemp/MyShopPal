package eu.tutorials.myshoppal.presentation.main.register

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.domain.use_case.register.RegisterUserUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.presentation.model.RegisterUser
import eu.tutorials.myshoppal.utils.Constants
import eu.tutorials.myshoppal.utils.toUserRegisterModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel<RegisterEvent, RegisterState, RegisterEffect>() {

    override fun createInitialState(): RegisterState {
        return RegisterState(
            viewState = ViewState.Idle
        )
    }

    override fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegister -> {
                registerUser(event.registerUser)
            }
        }
    }

    private fun setStateError(message: UiText) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { RegisterEffect.ShowSnackbar(message, Constants.STATUS_ERROR) }
    }

    private fun setStateSuccess(message: UiText) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { RegisterEffect.ShowToast(message) }
    }

    private fun registerUser(registerUser: RegisterUser) {
        viewModelScope.launch {
            if (validateRegisterDetails(registerUser)) {
                registerUserUseCase(registerUser.toUserRegisterModel())
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                    .collect { setStateSuccess(UiText.StringResource(R.string.success_register)) }
            }
        }
    }


    private fun validateRegisterDetails(registerUser: RegisterUser): Boolean {
        registerUser.apply {
            return when {
                TextUtils.isEmpty(firstName.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_first_name))
                    false
                }
                TextUtils.isEmpty(lastName.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_last_name))
                    false
                }
                TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_email))
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_password))
                    false
                }
                TextUtils.isEmpty(confirmPassword.trim { it <= ' ' }) -> {
                    setStateError(UiText.StringResource(R.string.error_password_confirm))
                    false
                }
                password.trim() { it <= ' ' } != confirmPassword.trim() { it <= ' ' } -> {
                    setStateError(UiText.StringResource(R.string.error_password_confirm_not_match))
                    false
                }
                !checkedTermsAndConditions -> {
                    setStateError(UiText.StringResource(R.string.error_terms_conditions))
                    false
                }
                else -> {
                    true
                }
            }
        }
    }
}