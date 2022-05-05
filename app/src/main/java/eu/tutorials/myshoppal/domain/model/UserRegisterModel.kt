package eu.tutorials.myshoppal.domain.model

data class UserRegisterModel(
    val id: String = "",
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)