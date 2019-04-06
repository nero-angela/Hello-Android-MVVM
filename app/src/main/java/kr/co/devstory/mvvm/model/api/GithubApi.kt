package kr.co.devstory.mvvm.model.api

import io.reactivex.Observable
import kr.co.devstory.mvvm.model.data.UserResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubApi {

    @GET
    fun getUserList(@Url url: String): Observable<UserResponse>
}
