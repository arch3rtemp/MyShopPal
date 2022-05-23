package eu.tutorials.myshoppal.presentation.main.profile

import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.myshoppal.R
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileLoadUserDiskUseCase
import eu.tutorials.myshoppal.domain.use_case.profile.ProfileUpdateUseCase
import eu.tutorials.myshoppal.presentation.base.BaseViewModel
import eu.tutorials.myshoppal.presentation.base.UiText
import eu.tutorials.myshoppal.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileLoadUserDiskUseCase: ProfileLoadUserDiskUseCase,
    private val profileUpdateUseCase: ProfileUpdateUseCase
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    private var user: UserModel = UserModel.Empty
    private var isCompleted: Int = -1

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
                .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                .onEach {
                    user = it
                    isCompleted = it.profileCompleted
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
                profileUpdateUseCase(selectedImageFileUri, fileExtension, user.id, userHashMap)
                    .onStart { setState { copy(viewState = ViewState.Loading) } }
                    .catch { setStateError(UiText.DynamicString(it.message.toString())) }
                    .collect {
                        setStateSuccess(UiText.StringResource(R.string.success_update))
                        setState { copy(viewState = ViewState.Idle) }
                        checkNavigation()
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
            TextUtils.isEmpty(userHashMap[Constants.FIRST_NAME].toString().trim { it <= ' ' }) -> {
                setStateError(UiText.StringResource(R.string.error_first_name))
                false
            }
            TextUtils.isEmpty(userHashMap[Constants.LAST_NAME].toString().trim { it <= ' ' }) -> {
                setStateError(UiText.StringResource(R.string.error_last_name))
                false
            }
            TextUtils.isEmpty(userHashMap[Constants.MOBILE].toString().trim { it <= ' ' }) -> {
                setStateError(UiText.StringResource(R.string.error_mobile_number))
                false
            }
            else -> {
                true
            }
        }
    }

    private fun checkNavigation() {
        if (isCompleted == 0) {
            setEffect { ProfileEffect.Finish }
        } else {
            setEffect { ProfileEffect.Pop }
        }
    }

    private fun setStateError(message: UiText) {
        setState { copy(viewState = ViewState.Error) }
        setEffect { ProfileEffect.ShowSnackbar(message, Constants.STATUS_ERROR) }
    }

    private fun setStateSuccess(message: UiText) {
        setState { copy(viewState = ViewState.Success) }
        setEffect { ProfileEffect.ShowToast(message) }
    }

}