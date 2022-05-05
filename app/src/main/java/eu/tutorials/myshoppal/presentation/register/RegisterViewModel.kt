package eu.tutorials.myshoppal.presentation.register

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.register.RegisterAndSaveUserUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.model.RegisterUser
import eu.tutorials.myshoppal.utils.toUserRegisterModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerAndSaveUserUseCase: RegisterAndSaveUserUseCase
) : BaseViewModel<RegisterEvent, RegisterState, RegisterEffect>() {

    override fun createInitialState(): RegisterState {
        return RegisterState(
            viewState = ViewState.Idle
        )
    }

    override fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegister -> { registerUser(event.registerUser) }
        }
    }

    private fun setStateError(message: String) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { RegisterEffect.Error(message) }
    }

    private fun setStateSuccess(message: String) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { RegisterEffect.Success(message) }
    }

    private fun registerUser(registerUser: RegisterUser) {
        viewModelScope.launch {
            if (validateRegisterDetails(registerUser)) {
                registerAndSaveUserUseCase(registerUser.toUserRegisterModel())
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(it.message.toString()) }
                    .collect { setStateSuccess("Registered successfully.") }
            }
        }
    }

    private fun validateRegisterDetails(registerUser: RegisterUser): Boolean {
        registerUser.apply {
            return when {
                TextUtils.isEmpty(firstName.trim { it <= ' '}) -> {
                    setStateError("Please enter first name.")
                    false
                }
                TextUtils.isEmpty(lastName.trim { it <= ' '}) -> {
                    setStateError("Please enter last name.")
                    false
                }
                TextUtils.isEmpty(email.trim { it <= ' '}) -> {
                    setStateError("Please enter an email id.")
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' '}) -> {
                    setStateError("Please enter a password.")
                    false
                }
                TextUtils.isEmpty(password.trim { it <= ' '}) -> {
                    setStateError("Please enter a confirm password.")
                    false
                }
                password.trim() { it <= ' '} != confirmPassword.trim() { it <= ' '} -> {
                    setStateError("Password and confirm password does not match")
                    false
                }
                !checkedTermsAndConditions -> {
                    setStateError("Please agree terms and conditions.")
                    false
                }
                else -> {
                    true
                }
            }
        }
    }
}