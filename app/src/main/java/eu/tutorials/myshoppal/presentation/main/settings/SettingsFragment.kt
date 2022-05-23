package eu.tutorials.myshoppal.presentation.main.settings

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentSettingsBinding
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.CoilLoader
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsEvent, SettingsState, SettingsEffect, FragmentSettingsBinding, SettingsViewModel>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

    override val viewModel by viewModels<SettingsViewModel>()

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setListeners()
        setupToolbar()
    }

    override fun renderState(state: SettingsState) {
        when (state.viewState) {
            ViewState.Idle ->  { showSettingsIdle() }
            ViewState.Loading -> { showSettingsLoading() }
            ViewState.Success -> {
                showSettingsSuccess()
                fillInData(state.user)
            }
            ViewState.Error -> { showSettingsError() }
        }
    }

    override fun renderEffect(effect: SettingsEffect) {
        when (effect) {
            is SettingsEffect.Error -> {
                showSnackbar(effect.message, true)
            }
            is SettingsEffect.Success -> {
                showSnackbar(effect.message, false)
            }
            SettingsEffect.Finish -> {
                val options = NavOptions
                    .Builder()
                    .setEnterAnim(R.anim.slide_in_left)
                    .setExitAnim(R.anim.slide_out_right)
                    .setPopUpTo(R.id.nav_graph_dashboard, true)
                    .build()
                findNavController().navigate(R.id.nav_graph_login, null, options)
            }
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.viewState is ViewState.Idle) {
            viewModel.setEvent(SettingsEvent.OnUserLoaded)
        }
    }

    private fun setListeners() = with(binding) {
        btnLogout.setOnClickListener {
            alertDialogLogout()
        }
        tvEdit.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToProfileFragment()
            findNavController().navigate(action)
        }
        llAddress.setOnClickListener {

        }
    }

    private fun setupToolbar() = with(binding) {
        ivBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun fillInData(user: UserModel) = with(binding) {
        tvSex.text = user.sex
        tvEmail.text = user.email
        tvMobileNumber.text = user.mobile.toString()
        tvName.text = String.format("${user.firstName} ${user.lastName}")
        if (user.image.isNotBlank()) {
            CoilLoader(requireContext()).loadUserPicture(Uri.parse(user.image), ivUserPhoto)
        }
    }

    private fun alertDialogLogout() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.app_name))
            .setMessage("Are you sure you want to Logout?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Yes") { dialogInterface, _ ->
                dialogInterface.dismiss()
                viewModel.setEvent(SettingsEvent.OnSignOut)
            }
            .setNegativeButton("No") {
                    dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()

        dialog.setCancelable(false)
        dialog.show()
    }

    private fun showSettingsIdle() = with(binding) {
        dismissProgressDialog()
    }

    private fun showSettingsLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showSettingsSuccess() = with(binding) {
        dismissProgressDialog()
    }

    private fun showSettingsError() = with(binding) {
        dismissProgressDialog()
    }
}