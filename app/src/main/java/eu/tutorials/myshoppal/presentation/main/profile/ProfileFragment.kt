package eu.tutorials.myshoppal.presentation.main.profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentProfileBinding
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.*
import java.io.IOException

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileEvent, ProfileState, ProfileEffect, FragmentProfileBinding, ProfileViewModel>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override val viewModel by viewModels<ProfileViewModel>()

    private var selectedImageFileUri: Uri = Uri.EMPTY
    private var firstName: String = ""
    private var lastName: String = ""

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            showImageChooser()
        } else {
            Toast
                .makeText(requireContext(), getString(R.string.read_storage_permission_denied), Toast.LENGTH_LONG)
                .show()
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                result.data?.let {
                    selectedImageFileUri = result.data?.data ?: Uri.EMPTY
                    CoilLoader(requireContext()).loadUserPicture(selectedImageFileUri, binding.ivUserPhoto)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.image_selection_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        setListeners()
        setupToolbar()
    }

    override fun renderState(state: ProfileState) {
        when (state.viewState) {
            ViewState.Idle -> { showProfileIdle() }
            ViewState.Loading -> { showProfileLoading() }
            ViewState.Error -> { showProfileError() }
            ViewState.Success -> {
                showProfileSuccess()
                fillInData(state.user)
                if (state.user.profileCompleted == 0) {
                    setupCompleteProfileScreen()
                }
            }
        }
    }

    override fun renderEffect(effect: ProfileEffect) {
        when (effect) {
            is ProfileEffect.ShowSnackbar -> {
                showSnackbar(effect.message.asString(requireContext()), effect.status)
            }

            is ProfileEffect.ShowToast -> {
                showToast(effect.message.asString(requireContext()))
            }
            ProfileEffect.Finish -> {
                val options = NavOptions
                    .Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .setPopUpTo(R.id.nav_graph_dashboard, true)
                    .build()

                findNavController().navigate(R.id.dashboardFragment, null, options)
            }
            ProfileEffect.Pop -> {
                findNavController().popBackStack()
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
            ivUserPhoto.setOnClickListener { checkReadPermission() }
            rbMale.setOnClickListener {  }
            rbFemale.setOnClickListener {  }
            btnSubmit.setOnClickListener {
                val fileExtension = getFileExtension(selectedImageFileUri)
                viewModel.setEvent(
                    ProfileEvent.OnUserEdited(
                        getDataFromFields(),
                        selectedImageFileUri,
                        fileExtension
                    )
                )
            }
        }
    }

    private fun setupToolbar() = with(binding) {
        ivBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun setupCompleteProfileScreen() = with(binding) {
        ivBack.visibility = View.GONE
        tvTitle.text = getString(R.string.title_complete_profile)

        etFirstName.isEnabled = false
        etLastName.isEnabled = false
    }

    private fun getDataFromFields() = with(binding) {
        val sex = if (rbMale.isChecked) {
            Constants.MALE
        } else {
            Constants.FEMALE
        }
        val userHashMap = HashMap<String, Any>()
        if (etFirstName.text.toString() != firstName) {
            userHashMap[Constants.FIRST_NAME] = etFirstName.text.toString()
        }
        if (etLastName.text.toString() != lastName) {
            userHashMap[Constants.LAST_NAME] = etLastName.text.toString()
        }
        userHashMap[Constants.MOBILE] = etMobileNumber.text.toString()
        userHashMap[Constants.SEX] = sex
        userHashMap[Constants.PROFILE_COMPLETED] = 1
        return@with userHashMap
    }

    private fun checkReadPermission() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R || Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP -> {
                showImageChooser()
            }
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                showImageChooser()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                showSnackbar(getString(R.string.image_selection_rationale), Constants.STATUS_ERROR)
            }
            else -> {
                requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun showImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private fun fillInData(user: UserModel) = with(binding) {

        etFirstName.setText(user.firstName)
        firstName = user.firstName
        etLastName.setText(user.lastName)
        lastName = user.lastName
        etEmail.isEnabled = false
        etEmail.setText(user.email)

        if (user.mobile != 0L) {
            etMobileNumber.setText(user.mobile.toString())
        }

        if (user.sex == Constants.MALE) {
            rbMale.isChecked = true
        } else if (user.sex == Constants.FEMALE) {
            rbFemale.isChecked = true
        }

        if (user.image.isNotBlank()) {
            CoilLoader(requireContext()).loadUserPicture(Uri.parse((user.image)), ivUserPhoto)
        }
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