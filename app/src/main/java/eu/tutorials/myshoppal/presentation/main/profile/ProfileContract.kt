package eu.tutorials.myshoppal.presentation.main.profile

import android.net.Uri
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.presentation.base.UiEffect
import eu.tutorials.myshoppal.presentation.base.UiEvent
import eu.tutorials.myshoppal.presentation.base.UiState

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    object Success : ViewState()
    object Error : ViewState()
}

sealed class ProfileEvent : UiEvent {
    object OnLoadUser: ProfileEvent()
    data class OnUserEdited(val userHashMap: HashMap<String, Any>, val selectedImageFileUri: Uri?, val fileExtension: String?) : ProfileEvent()
}

sealed class ProfileEffect : UiEffect {
    data class Success(val message: String) : ProfileEffect()
    data class Error(val message: String) : ProfileEffect()
    object Finish : ProfileEffect()
}

data class ProfileState(
    val viewState: ViewState,
    val user: UserModel = UserModel.Empty
) : UiState