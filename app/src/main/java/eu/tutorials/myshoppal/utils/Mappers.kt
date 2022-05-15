package eu.tutorials.myshoppal.utils

import eu.tutorials.myshoppal.domain.model.UserLoginModel
import eu.tutorials.myshoppal.domain.model.UserModel
import eu.tutorials.myshoppal.domain.model.UserRegisterModel
import eu.tutorials.myshoppal.presentation.model.LoginUser
import eu.tutorials.myshoppal.presentation.model.ProfileUser
import eu.tutorials.myshoppal.presentation.model.RegisterUser

fun RegisterUser.toUserRegisterModel(): UserRegisterModel {
    return UserRegisterModel(
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password
    )
}

fun LoginUser.toUserLoginModel(): UserLoginModel {
    return UserLoginModel(
        email = email,
        password = password
    )
}

fun ProfileUser.toUserModel(): UserModel {
    return UserModel(
        mobile = mobile.toLong(),
        sex = sex
    )
}