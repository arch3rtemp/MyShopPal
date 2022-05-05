package eu.tutorials.myshoppal.data.remote.model

data class UserDataModel(
    val id : String = "",
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val image : String = "",
    val mobile : Long = 0,
    val gender : String = "",
    val profileCompleted : Int = 0
)
