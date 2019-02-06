package nizar.id.github.data

import io.reactivex.Flowable
import nizar.id.github.data.model.RepositoryModel
import nizar.id.github.data.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRoute {

    @GET("users/{username}")
    fun getUsers(
            @Path("username") username: String
    ): Flowable<UserModel>

    @GET("users/{username}/repos")
    fun getRepositories(
            @Path("username") username: String
    ): Flowable<List<RepositoryModel>>

}