package nizar.id.github.mvp.repositories

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_repository.view.*
import nizar.id.github.R
import nizar.id.github.data.model.RepositoryModel
import nizar.id.github.utils.getDate
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

import nizar.id.github.utils.JSONResourceReader


class RepositoriesAdapter(
        private val mData: List<RepositoryModel>, private val listener: RepositoryListener
) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesHolder = RepositoriesHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_repository,
                    parent,
                    false)
    )

    override fun getItemCount(): Int = mData.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: RepositoriesHolder, position: Int) {
        val data = mData[position]
        holder.bindView(data, listener)
    }

    class RepositoriesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(repositoryModel: RepositoryModel, listener: RepositoryListener) {

            with(repositoryModel) {
                itemView.title.text = name
                if (description != "" && description != null) {
                    itemView.desc.text = description
                } else {
                    itemView.desc.text = "-"
                }
                itemView.date.text = itemView.context.getString(R.string.date_update_on, getDate(updated_at))
                itemView.branch.text = forks_count.toString()
                itemView.watcher.text = watchers_count.toString()
                if (language != "" && language != null) {
                    itemView.language.text = language
                    itemView.language.visibility = View.VISIBLE
                    itemView.imageLanguage.visibility = View.VISIBLE
                    setDrawableTint(itemView.rootView.context, language, itemView.imageLanguage)
                }
                itemView.setOnClickListener {
                    listener.onClickItem(repositoryModel)
                }

            }
        }

        fun setDrawableTint(context: Context, language: String, imageView: ImageView) {
            DrawableCompat.setTint(
                    imageView.getDrawable().mutate(), Color.parseColor(loadColor(context, language))
            );
        }

        fun loadColor(context: Context, language: String): String {
            var colorString = "#FF595959"
            try {
                val reader = JSONResourceReader(context.getResources(), R.raw.colors)
                val jsonObj = reader.constructUsingGson(HashMap<String, String>()::class.java)
                colorString = jsonObj?.getValue(language) ?: "#FF595959"
            } catch (e: Exception) {
            }
            return colorString
        }


    }

}


