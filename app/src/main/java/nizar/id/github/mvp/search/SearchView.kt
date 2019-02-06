package nizar.id.github.mvp.search

import nizar.id.github.data.model.HistoryModel
import nizar.id.github.data.model.UserModel
import nizar.id.github.mvp.base.View

interface SearchView: View {

    fun onProgress()

    fun onSuccess(response: UserModel)

    fun onFail()

    fun onHistoryLoaded(response: List<HistoryModel>)
}