package eu.tutorials.myshoppal.presentation.main.recover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentRecoverBinding
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class RecoverFragment : BaseFragment<RecoverEvent, RecoverState, RecoverEffect, FragmentRecoverBinding, RecoverViewModel>() {
    override val viewModel by viewModels<RecoverViewModel>()
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecoverBinding
        get() = FragmentRecoverBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        setListeners()
    }

    override fun renderState(state: RecoverState) {
        when (state.viewState) {
            ViewState.Idle -> { showLoginIdle() }
            ViewState.Loading -> { showLoginLoading() }
            ViewState.Success -> {
                showLoginSuccess()
                findNavController().navigateUp()
            }
            ViewState.Error -> { showLoginError() }
        }
    }

    override fun renderEffect(effect: RecoverEffect) {
        when (effect) {
            is RecoverEffect.Success -> { showSnackbar(effect.message, false) }
            is RecoverEffect.Error -> { showSnackbar(effect.message, true) }
        }
    }

    private fun setListeners() {
        with(binding) {
            ivBack.setOnClickListener { findNavController().navigateUp() }
            btnSubmit.setOnClickListener { viewModel.setEvent(RecoverEvent.OnRecover(etEmail.text.toString())) }
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
}