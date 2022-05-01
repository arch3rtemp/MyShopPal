package eu.tutorials.myshoppal.data.remote.model

data class UserRegisterDataModel(
    val id: String = "",
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)