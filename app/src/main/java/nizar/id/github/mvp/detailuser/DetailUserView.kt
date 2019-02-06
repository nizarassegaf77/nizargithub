package nizar.id.github.mvp.detailuser

import nizar.id.github.data.model.UserModel
import nizar.id.github.mvp.base.View

interface DetailUserView: View {

    fun onUserLoaded(userModel: UserModel)

}