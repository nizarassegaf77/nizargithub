package nizar.id.github.mvp.detailuser

import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import nizar.id.github.data.model.UserModel
import nizar.id.github.mvp.base.Presenter
import nizar.id.github.utils.safeDispose

class DetailUserPresenter : Presenter<DetailUserView> {

    private var view: DetailUserView? = null
    private var disposables: CompositeDisposable? = null

    override fun onAttach(view: DetailUserView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        view = null
        disposables.safeDispose()
    }

    fun loadDataUser(userModel:String){
        try {
            view?.onUserLoaded(userModel = Gson().fromJson(userModel, UserModel::class.java))
        } catch (e: Exception) {
        }
    }
}