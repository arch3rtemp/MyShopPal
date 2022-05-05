package eu.tutorials.myshoppal.data.remote.util

import eu.tutorials.myshoppal.data.remote.model.UserDataModel
import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel

fun UserRegisterDataModel.toUserDataModel(id: String): UserDataModel {
    return UserDataModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}

fun UserRegisterDataModel.toUserRegisterModel(): UserRegisterModel {
    return UserRegisterModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password
    )
}

fun UserRegisterModel.toUserRegisterDataModel(): UserRegisterDataModel {
    return UserRegisterDataModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password
    )
}

fun UserModel.toUserDataModel(): UserDataModel {
    return UserDataModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        image = image,
        mobile = mobile,
        gender = gender,
        profileCompleted = profileCompleted
    )
}

fun UserDataModel.toUserModel(): UserModel {
    return UserModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        image = image,
        mobile = mobile,
        gender = gender,
        profileCompleted = profileCompleted
    )
}

fun UserLoginModel.toUserLoginDataModel(): UserLoginDataModel {
    return UserLoginDataModel(
        email = email,
        password = password
    )
}