package eu.tutorials.myshoppal.utils

import eu.tutorials.myshoppal.data.remote.model.UserLoginDataModel
import eu.tutorials.myshoppal.data.remote.model.UserRegisterDataModel
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.presentation.model.RegisterUser

fun RegisterUser.toUserAuthModel(): UserRegisterDataModel {
    return UserRegisterDataModel(
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password
    )
}

fun LoginUser.toUserAuthModel(): UserLoginDataModel {
    return UserLoginDataModel(
        email = email,
        password = password
    )
}