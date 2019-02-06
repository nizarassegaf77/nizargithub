package nizar.id.github.mvp.repositories

import nizar.id.github.data.model.RepositoryModel


interface RepositoryListener{

    fun onClickItem(userModel: RepositoryModel)
}