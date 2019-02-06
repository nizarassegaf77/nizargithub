package nizar.id.github.data.service

import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import nizar.id.github.data.ApiRoute
import nizar.id.github.utils.disposableSubscriber
import io.reactivex.functions.Function
import nizar.id.github.data.model.RepositoryModel
import nizar.id.github.data.model.UserModel

class GitHubServices(private val api: ApiRoute) {

    fun getUser(key: String,
                 callback: NetworkCallback<UserModel>): Disposable {
        return api.getUsers(key)
                .compose(NetworkCallTransformer<UserModel>())
                .onErrorResumeNext(Function {
                    Flowable.error { it }
                })
                .subscribeWith(disposableSubscriber<UserModel>(
                        next = { response -> callback.onSuccess(response) },
                        error = { exception -> callback.onError(exception) }

                ))
    }

    fun getRepositories(username: String,
                        callback: NetworkCallback<List<RepositoryModel>>): Disposable {
        return api.getRepositories(username)
                .compose(NetworkCallTransformer<List<RepositoryModel>>())
                .onErrorResumeNext(Function { Flowable.error { it } })
                .subscribeWith(disposableSubscriber<List<RepositoryModel>>(
                        next = { response -> callback.onSuccess(response) },
                        error = { exception -> callback.onError(exception) }

                ))

    }

}