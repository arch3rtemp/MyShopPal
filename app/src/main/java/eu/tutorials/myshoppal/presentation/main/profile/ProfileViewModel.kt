package eu.tutorials.myshoppal.presentation.main.profile

import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileLoadUserDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileUpdateUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileUpdateUserUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileUploadImageUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileLoadUserDiskUseCase: ProfileLoadUserDiskUseCase,
    private val profileUpdateUserUseCase: ProfileUpdateUserUseCase,
    private val profileUploadImageUseCase: ProfileUploadImageUseCase,
    private val profileUpdateUseCase: ProfileUpdateUseCase
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    private var userId: String = ""

    override fun createInitialState(): ProfileState {
        return ProfileState(viewState = ViewState.Idle)
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnLoadUser -> {
                loadUser()
            }
            is ProfileEvent.OnUserEdited -> {
                updateUser(event.userHashMap, event.selectedImageFileUri, event.fileExtension)
            }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            profileLoadUserDiskUseCase()
                .onStart { setState { copy(viewState = ViewState.Loading) } }
                .catch { setStateError(it.message.toString()) }
                .onEach {
                    userId = it.id
                    setState { copy(viewState = ViewState.Success, user = it) }
                }
                .collect {
                    setState { copy(viewState = ViewState.Idle) }
                }
        }
    }

    private fun updateUser(userHashMap: HashMap<String, Any>, selectedImageFileUri: Uri?, fileExtension: String?) {
        viewModelScope.launch {
            if (validateUserProfileDetails(userHashMap)) {
                convertMobileToLong(userHashMap)
                profileUpdateUseCase(selectedImageFileUri, fileExtension, userId, userHashMap)
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(it.message.toString()) }
                    .collect {
                        setStateSuccess("Updated successfully.")
                        setState { copy(viewState = ViewState.Idle) }
                        setEffect { ProfileEffect.Finish }
                    }
            }
        }
    }

    private fun convertMobileToLong(userHashMap: HashMap<String, Any>) {
        val mobile = userHashMap[Constants.MOBILE].toString().toLong()
        userHashMap[Constants.MOBILE] = mobile
    }

    private fun validateUserProfileDetails(userHashMap: HashMap<String, Any>): Boolean {
        return when {
            TextUtils.isEmpty(userHashMap[Constants.MOBILE].toString().trim { it <= ' ' }) -> {
                setStateError("Please enter mobile number")
                false
            }
            else -> {
                true
            }
        }
    }

    private fun setStateError(message: String) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { ProfileEffect.Error(message) }
    }

    private fun setStateSuccess(message: String) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { ProfileEffect.Success(message) }
    }

}