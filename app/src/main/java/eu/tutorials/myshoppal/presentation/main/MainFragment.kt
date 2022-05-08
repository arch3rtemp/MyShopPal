package eu.tutorials.myshoppal.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentMainBinding
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainEvent, MainState, MainEffect, FragmentMainBinding, MainViewModel>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override val viewModel by viewModels<MainViewModel>()

    override fun prepareView(savedInstanceState: Bundle?) {
        if (viewModel.currentState.viewState is ViewState.Idle) {
            viewModel.setEvent(MainEvent.OnUserLoaded)
        }
    }

    override fun renderState(state: MainState) {
        when (state.viewState) {
            ViewState.Idle -> { showMainIdle() }
            ViewState.Loading -> { showMainLoading() }
            ViewState.Success -> { showMainSuccess(state.name) }
            ViewState.Error -> { showMainError() }
        }
    }

    override fun renderEffect(effect: MainEffect) {
        when (effect) {
            is MainEffect.Success -> {
                showSnackbar(effect.message, false)
            }
            is MainEffect.Error -> {
                showSnackbar(effect.message, true)
            }
        }
    }

    private fun showMainIdle() = with(binding) {
        dismissProgressDialog()
    }

    private fun showMainLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showMainSuccess(name: String) = with(binding) {
        dismissProgressDialog()
        tvUserName.text = name
    }

    private fun showMainError() = with(binding) {
        dismissProgressDialog()
    }
}