package eu.tutorials.myshoppal.presentation.main.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentRegisterBinding
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.presentation.model.RegisterUser
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterEvent, RegisterState, RegisterEffect, FragmentRegisterBinding, RegisterViewModel>() {

    override val viewModel by viewModels<RegisterViewModel>()
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        setListeners()
    }

    override fun renderState(state: RegisterState) {
        when (state.viewState) {
            ViewState.Idle -> { showRegisterIdle() }
            ViewState.Loading -> { showRegisterLoading() }
            ViewState.Success -> {
                showRegisterSuccess()
                findNavController().navigateUp()
            }
            ViewState.Error -> { showRegisterError() }
        }
    }

    override fun renderEffect(effect: RegisterEffect) {
        when (effect) {
            is RegisterEffect.Success -> { showSnackbar(effect.message, false) }
            is RegisterEffect.Error -> { showSnackbar(effect.message, true) }
        }
    }

    private fun setListeners() {
        with(binding) {
            ivBack.setOnClickListener { findNavController().navigateUp() }
            tvLogin.setOnClickListener { findNavController().navigateUp() }
            btnRegister.setOnClickListener { viewModel.setEvent(
                RegisterEvent.OnRegister(
                    getDataFromFields()
                )
            ) }
        }
    }

    private fun getDataFromFields() = with(binding) {
        return@with RegisterUser(
            firstName = etFirstName.text.toString(),
            lastName = etLastName.text.toString(),
            email = etEmail.text.toString(),
            password = etPassword.text.toString(),
            confirmPassword = etConfirmPassword.text.toString(),
            checkedTermsAndConditions = cbTermsAndCondition.isChecked
        )
    }

    private fun showRegisterIdle() = with(binding) {

    }

    private fun showRegisterLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }
    private fun showRegisterSuccess() = with(binding) {
        dismissProgressDialog()
    }
    private fun showRegisterError() = with(binding) {
        dismissProgressDialog()
    }
}