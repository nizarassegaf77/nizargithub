package nizar.id.github.data.service

interface NetworkCallback<in T> {

    fun onSuccess(response: T)

    fun onError(e: Throwable)
}