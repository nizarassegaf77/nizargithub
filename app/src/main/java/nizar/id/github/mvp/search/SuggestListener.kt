package nizar.id.github.mvp.search

import nizar.id.github.data.model.HistoryModel

interface SuggestListener{

    fun onClickSuggest(item:String)

    fun onClickRemoveSuggest(historyModel: HistoryModel)

}