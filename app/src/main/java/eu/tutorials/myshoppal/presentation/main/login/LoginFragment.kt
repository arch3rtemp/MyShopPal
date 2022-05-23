package eu.tutorials.myshoppal.presentation.main.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentLoginBinding
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<LoginEvent, LoginState, LoginEffect, FragmentLoginBinding, LoginViewModel>() {

    override val viewModel by viewModels<LoginViewModel>()
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        setListeners()
    }

    override fun renderState(state: LoginState) {
        when (state.viewState) {
            ViewState.Idle -> {
                showLoginIdle()
            }
            ViewState.Loading -> {
                showLoginLoading()
            }
            ViewState.Success -> {
                showLoginSuccess()
            }
            ViewState.Error -> {
                showLoginError()
            }
        }
    }

    override fun renderEffect(effect: LoginEffect) {
        when (effect) {
            is LoginEffect.ShowSnackbar -> {
                showSnackbar(effect.message.asString(requireContext()), effect.status)
            }
            is LoginEffect.Finish -> {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToNavGraphDashboard()
                findNavController().navigate(action)
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            tvRegister.setOnClickListener {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
            btnLogin.setOnClickListener {
                viewModel.setEvent(LoginEvent.OnLogin(getDataFromFields()))
            }
            tvForgotPassword.setOnClickListener {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToRecoverFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun getDataFromFields() = with(binding) {
        return@with LoginUser(
            email = etEmail.text.toString(),
            password = etPassword.text.toString()
        )
    }

    private fun showLoginIdle() = with(binding) {
        dismissProgressDialog()
    }

    private fun showLoginLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showLoginSuccess() = with(binding) {
        dismissProgressDialog()
    }

    private fun showLoginError() = with(binding) {
        dismissProgressDialog()
    }
}