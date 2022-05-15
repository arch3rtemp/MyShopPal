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
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.databinding.FragmentProfileBinding
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.BaseFragment
import eu.tutorials.myshoppal.utils.CoilLoader
import eu.tutorials.myshoppal.utils.Constants
import eu.tutorials.myshoppal.utils.getFileExtension
import eu.tutorials.myshoppal.utils.showSnackbar
import java.io.IOException

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileEvent, ProfileState, ProfileEffect, FragmentProfileBinding, ProfileViewModel>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override val viewModel by viewModels<ProfileViewModel>()

    private var selectedImageFileUri: Uri? = null

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
                    selectedImageFileUri = result.data?.data
                    CoilLoader(requireContext()).loadUserPicture(selectedImageFileUri!!, binding.ivUserPhoto)
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
    }

    override fun renderState(state: ProfileState) {
        when (state.viewState) {
            ViewState.Idle -> { showProfileIdle() }
            ViewState.Loading -> { showProfileLoading() }
            ViewState.Error -> { showProfileError() }
            ViewState.Success -> {
                showProfileSuccess()
                fillInData(state.user)
            }
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
            ProfileEffect.Finish -> {
                val action = ProfileFragmentDirections.actionProfileFragmentToDashboardActivity()
                findNavController().navigate(action)
                requireActivity().finish()
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
                viewModel.setEvent(ProfileEvent.OnUserEdited(getDataFromFields(), selectedImageFileUri, fileExtension))
            }
        }
    }

    private fun getDataFromFields() = with(binding) {
        val sex = if (rbMale.isChecked) {
            Constants.MALE
        } else {
            Constants.FEMALE
        }
        val userHashMap = HashMap<String, Any>()
        userHashMap[Constants.MOBILE] = etMobileNumber.text.toString()
        userHashMap[Constants.GENDER] = sex
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
                showSnackbar(getString(R.string.image_selection_rationale), false)
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
        etFirstName.isEnabled = false
        etFirstName.setText(user.firstName)

        etLastName.isEnabled = false
        etLastName.setText(user.lastName)

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

        CoilLoader(requireContext()).loadUserPicture(Uri.parse((user.image)), ivUserPhoto)
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