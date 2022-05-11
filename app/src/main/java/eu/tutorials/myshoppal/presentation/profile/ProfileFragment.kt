package eu.tutorials.myshoppal.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentProfileBinding
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileEvent, ProfileState, ProfileEffect, FragmentProfileBinding, ProfileViewModel>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override val viewModel by viewModels<ProfileViewModel>()

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setListeners()
    }

    override fun renderState(state: ProfileState) {
        when (state.viewState) {
            ViewState.Idle -> { showProfileIdle() }
            ViewState.Loading -> { showProfileLoading() }
            ViewState.Error -> { showProfileError() }
            ViewState.Success -> { showProfileSuccess()
                fillInData(state.user) }
        }
    }

    override fun renderEffect(effect: ProfileEffect) {
        when (effect) {
            is ProfileEffect.Error -> {
                showSnackbar(effect.message, false)
            }
            is ProfileEffect.Success -> {
                showSnackbar(effect.message, true)
            }
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.viewState is ViewState.Idle) {
            viewModel.setEvent(ProfileEvent.OnLoadUser)
        }
    }

    private fun setListeners() {
        with(binding) {
            ivUserPhoto.setOnClickListener {  }
            rbMale.setOnClickListener {  }
            rbFemale.setOnClickListener {  }
            btnSubmit.setOnClickListener {  }
        }
    }

    private fun fillInData(user: UserModel) = with(binding) {
        etFirstName.isEnabled = false
        etFirstName.setText(user.firstName)

        etLastName.isEnabled = false
        etLastName.setText(user.lastName)

        etEmail.isEnabled = false
        etEmail.setText(user.email)
    }

    private fun showProfileIdle() = with(binding) {
        dismissProgressDialog()
    }

    private fun showProfileLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showProfileSuccess() = with(binding) {
        dismissProgressDialog()
    }

    private fun showProfileError() = with(binding) {
        dismissProgressDialog()
    }
}