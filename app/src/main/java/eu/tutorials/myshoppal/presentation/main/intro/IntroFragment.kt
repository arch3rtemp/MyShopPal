package eu.tutorials.myshoppal.presentation.main.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentIntroBinding
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class IntroFragment : BaseFragment<IntroEvent, IntroState, IntroEffect, FragmentIntroBinding, IntroViewModel>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIntroBinding
        get() = FragmentIntroBinding::inflate
    override val viewModel by viewModels<IntroViewModel>()

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
    }

    override fun renderState(state: IntroState) {
        when (state.viewState) {
            ViewState.Idle -> { showIntroIdle() }
            ViewState.Loading -> { showIntroLoading() }
            ViewState.Success -> {
                showIntroSuccess()
                checkLogin(state.user)
            }
            ViewState.Error -> { showIntroError() }
        }
    }

    override fun renderEffect(effect: IntroEffect) {
        when (effect) {
            is IntroEffect.Error -> showSnackbar(effect.message, true)
            is IntroEffect.Success -> showSnackbar(effect.message, false)
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.viewState is ViewState.Idle) {
            viewModel.setEvent(IntroEvent.OnUserLoaded)
        }
    }

    private fun checkLogin(user: FirebaseUser?) {
        if (user == null) {
            val action = IntroFragmentDirections.actionIntroFragmentToNavGraphLogin()
            findNavController().navigate(action)
        } else {
            val action = IntroFragmentDirections.actionIntroFragmentToNavGraphDashboard()
            findNavController().navigate(action)
        }
    }

    private fun showIntroIdle() = with(binding) {
        dismissProgressDialog()
    }

    private fun showIntroLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showIntroSuccess() = with(binding) {
        dismissProgressDialog()
    }

    private fun showIntroError() = with(binding) {
        dismissProgressDialog()
    }
}