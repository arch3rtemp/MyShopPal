package eu.tutorials.myshoppal.presentation.main.dashboard

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentDashboardBinding
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.showSnackbar

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardEvent, DashboardState, DashboardEffect, FragmentDashboardBinding, DashboardViewModel>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDashboardBinding
        get() = FragmentDashboardBinding::inflate

    override val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dashboard, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_settings -> {
                val action = DashboardFragmentDirections.actionDashboardFragmentToSettingsFragment()
                findNavController().navigate(action)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setListeners()
    }

    override fun renderState(state: DashboardState) {
        when (state.viewState) {
            ViewState.Idle -> { showDashboardIdle() }
            ViewState.Loading -> { showDashboardLoading() }
            ViewState.Success -> {
                showDashboardSuccess()
                checkProfileCompleted(state.user.profileCompleted)
            }
            ViewState.Error -> { showDashboardError() }
        }
    }

    private fun checkProfileCompleted(profileCompleted: Int) {
        if (profileCompleted == 0) {
            val options = NavOptions
                .Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .setPopUpTo(R.id.nav_graph_dashboard, true)
                .build()
            findNavController().navigate(R.id.profileFragment, null, options)
        }
        viewModel.setEvent(DashboardEvent.OnUserChecked)
    }

    override fun renderEffect(effect: DashboardEffect) {
        when (effect) {
            is DashboardEffect.ShowSnackbar -> showSnackbar(effect.message.asString(requireContext()), effect.status)
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.viewState is ViewState.Idle) {
            viewModel.setEvent(DashboardEvent.OnUserLoaded)
        }
    }

    private fun setListeners() {

    }

    private fun showDashboardIdle() = with(binding) {
        dismissProgressDialog()
    }

    private fun showDashboardLoading() = with(binding) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    private fun showDashboardSuccess() = with(binding) {
        dismissProgressDialog()
    }

    private fun showDashboardError() = with(binding) {
        dismissProgressDialog()
    }
}