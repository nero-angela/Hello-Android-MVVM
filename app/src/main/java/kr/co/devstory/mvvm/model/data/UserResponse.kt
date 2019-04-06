package kr.co.devstory.mvvm.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(
    @SerializedName("results")
    val userList: ArrayList<User>? = null
) {
    data class User(
        var gender: String?,
        var name: Name?,
        var location: Location?,
        var email: String?,
        var login: Login?,
        var phone: String?,
        var cell: String?,
        var picture: Picture?,
        var likeCnt: Int = 0

    ) : Serializable {
        val fullName: String
            get() = name?.title + "." + name?.first + " " + name?.last
    }

    data class Name(
        var title: String?,
        var first: String?,
        var last: String?
    ) : Serializable

    data class Location(
        var street: String?,
        var city: String?,
        var state: String?,
        var postcode: String?
    ) : Serializable

    data class Login(
        var username: String,
        var password: String,
        var salt: String,
        var md5: String,
        var sha1: String,
        var sha256: String
    ) : Serializable

    data class Picture (
        var large: String?,
        var medium: String?,
        var thumbnail: String?
    ) : Serializable
}
