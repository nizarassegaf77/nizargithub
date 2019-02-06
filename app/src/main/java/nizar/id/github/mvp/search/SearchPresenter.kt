package nizar.id.github.mvp.search

import android.util.Log
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import nizar.id.github.data.model.HistoriesModel
import nizar.id.github.data.model.HistoryModel
import nizar.id.github.data.model.UserModel
import nizar.id.github.data.service.GitHubServices
import nizar.id.github.data.service.NetworkCallback
import nizar.id.github.mvp.base.Presenter
import nizar.id.github.utils.StringPreference
import nizar.id.github.utils.safeDispose

class SearchPresenter : Presenter<SearchView> {

    private var view: SearchView? = null
    private var disposables: CompositeDisposable? = null

    override fun onAttach(view: SearchView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        view = null
        disposables.safeDispose()
    }

    fun getHistory(key: String, pref: StringPreference) {
        val data = pref.get()
        if (pref.get() != "") {
            try {
                val prefHistory = Gson().fromJson(pref.get(), HistoriesModel::class.java)
                val listHistory: ArrayList<HistoryModel> = ArrayList()
                if (prefHistory.histories.size > 0) {
                    prefHistory.histories.asReversed().forEach {
                        if (it.item?.toLowerCase()?.contains(key.replace(" ", "").toLowerCase())
                                        ?: false) {
                            listHistory.add(it)
                        }
                    }
                    view?.onHistoryLoaded(listHistory)
                }
            } catch (e: Exception) {
            }
        }
    }

    fun removeHistory(pref: StringPreference, query: String) {
        val listHistory: ArrayList<HistoryModel> = ArrayList()
        if (pref.get() != "") {
            try {
                val prefHistory = Gson().fromJson(pref.get(), HistoriesModel::class.java)
                if (prefHistory.histories.size > 0) {
                    listHistory.addAll(prefHistory.histories)
                }
            } catch (e: Exception) {
            }
        }
        listHistory.remove(HistoryModel(query))
        pref.set(Gson().toJson(HistoriesModel(listHistory)))
    }

    fun savetHistory(pref: StringPreference, query: String) {
        val listHistory: ArrayList<HistoryModel> = ArrayList()
        if (pref.get() != "") {
            try {
                val prefHistory = Gson().fromJson(pref.get(), HistoriesModel::class.java)
                if (prefHistory.histories.size > 0) {
                    listHistory.addAll(prefHistory.histories)
                }
            } catch (e: Exception) {
            }
        }
        listHistory.remove(HistoryModel(query))
        listHistory.add(HistoryModel(query))
        pref.set(Gson().toJson(HistoriesModel(listHistory)))
    }

    fun getUsers(key: String, service: GitHubServices) {
        view?.onProgress()

        val disposable = service.getUser(key, object : NetworkCallback<UserModel> {
            override fun onSuccess(response: UserModel) {
                view?.onSuccess(response)
            }

            override fun onError(e: Throwable) {
                view?.onFail()
            }

        })

        disposable.let { disposables?.add(it) }
    }

}