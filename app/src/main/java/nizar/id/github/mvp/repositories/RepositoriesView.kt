package nizar.id.github.mvp.repositories

import nizar.id.github.data.model.RepositoryModel
import nizar.id.github.mvp.base.View

interface RepositoriesView: View {

    fun onProgress()

    fun onFailed(message:String)

    fun onRepositoriesLoaded(response: List<RepositoryModel>)

}