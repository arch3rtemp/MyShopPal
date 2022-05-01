package eu.tutorials.myshoppal.presentation.model

data class RegisterUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val checkedTermsAndConditions: Boolean
)