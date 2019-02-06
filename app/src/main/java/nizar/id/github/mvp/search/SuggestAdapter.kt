package nizar.id.github.mvp.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_suggest.view.*
import nizar.id.github.R
import nizar.id.github.data.model.HistoryModel

class SuggestAdapter(
        private val histories: List<HistoryModel>,
        private val listener: SuggestListener
) : RecyclerView.Adapter<HomeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder = HomeHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_suggest,
                    parent,
                    false)
    )

    override fun getItemCount(): Int = histories.size


    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val article = histories[position]
        holder.bindView(article, listener)
    }

}

class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(
            text: HistoryModel, listener: SuggestListener) {

        with(text) {
            itemView.text_suggest.text = this.item
            itemView.remove.setOnClickListener {
                listener.onClickRemoveSuggest(HistoryModel(this.item))
            }
            itemView.setOnClickListener {
                listener.onClickSuggest(this.item.toString())
            }
        }
    }

}
