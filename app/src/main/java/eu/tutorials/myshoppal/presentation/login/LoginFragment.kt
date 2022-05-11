package eu.tutorials.myshoppal.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentLoginBinding
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<LoginEvent, LoginState, LoginEffect, FragmentLoginBinding, LoginViewModel>() {

    override val viewModel by viewModels<LoginViewModel>()
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private var backPressedTimestamp: Long = 0L

    override fun prepareView(savedInstanceState: Bundle?) {
        setListeners()
        initBackPressedCallback()
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
                checkUserProfile(state.user)
            }
            ViewState.Error -> {
                showLoginError()
            }
        }
    }

    override fun renderEffect(effect: LoginEffect) {
        when (effect) {
            is LoginEffect.Success -> {
                showSnackbar(effect.message, false)
            }
            is LoginEffect.Error -> {
                showSnackbar(effect.message, true)
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            tvRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
            btnLogin.setOnClickListener {
                viewModel.setEvent(LoginEvent.OnLogin(getDataFromFields()))
            }
            tvForgotPassword.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRecoverFragment()
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

    private fun checkUserProfile(user: UserModel) {
        if (user.profileCompleted == 0) {
            val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
            findNavController().navigate(action)
        } else {
            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
            findNavController().navigate(action)
        }
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

    private fun initBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (backPressedTimestamp + TIME_INTERVAL > System.currentTimeMillis()) {
                isEnabled = false
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Press back again to exit",
                    Toast.LENGTH_SHORT
                ).show()
            }
            backPressedTimestamp = System.currentTimeMillis()
        }
    }

    companion object {
        private const val TIME_INTERVAL = 2000
    }
}