package nizar.id.github.mvp.repositories

import io.reactivex.disposables.CompositeDisposable
import nizar.id.github.data.model.HistoriesModel
import nizar.id.github.data.model.RepositoryModel
import nizar.id.github.data.service.GitHubServices
import nizar.id.github.data.service.NetworkCallback
import nizar.id.github.mvp.base.Presenter
import nizar.id.github.mvp.search.SearchView
import nizar.id.github.utils.safeDispose

class RepositoriesPresenter : Presenter<RepositoriesView> {

    private var view: RepositoriesView? = null
    private var disposables: CompositeDisposable? = null

    override fun onAttach(view: RepositoriesView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        view = null
        disposables.safeDispose()
    }

    fun getRepositories(key: String, service: GitHubServices) {
        view?.onProgress()

        val disposable = service.getRepositories(key, object : NetworkCallback<List<RepositoryModel>> {

            override fun onSuccess(response: List<RepositoryModel>) {

                view?.onRepositoriesLoaded(response)
            }

            override fun onError(e: Throwable) {
                view?.onFailed(e.message.toString())
            }

        })

        disposable.let { disposables?.add(it) }
    }

}