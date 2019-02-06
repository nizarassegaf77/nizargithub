package nizar.id.github.utils

import android.content.Context
import android.content.res.Resources
import android.text.format.DateFormat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.util.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import nizar.id.github.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter


/**
 *
 * Created by Nizar Assegaf on 3/2/2019.
 *
 */

const val DEFAULT_DATE = "dd MMMM yyyy"
const val KEY_USERNAME = "USERNAME"
const val NAME_USERMODEL = "NAME_USERMODEL"

internal inline fun <reified T : Any> clazz() = T::class.java

internal fun getDate(date: String?): String {
    val tz = TimeZone.getTimeZone("UTC")
    val df = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    df.timeZone = tz

    return DateFormat.format("MMM, dd-yyyy", df.parse(date)).toString()
}

internal fun loadImage(context: Context, url: String?, imageView: ImageView) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)
    Glide.with(context)
            .load(url)
            .apply(RequestOptions().transforms(CenterCrop())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .into(imageView)
}

internal fun loadRoundedBitmap(context: Context, url: String?, imageView: ImageView) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)
    Glide.with(context)
            .asBitmap()
            .load(url)
            .apply(RequestOptions().circleCrop().placeholder(R.drawable.image_github))
            .into(imageView)

}

class JSONResourceReader
(resources: Resources, id: Int) {

    private val jsonString: String

    init {
        val resourceReader = resources.openRawResource(id)
        val writer = StringWriter()
        try {
            val reader = BufferedReader(InputStreamReader(resourceReader, "UTF-8"))
            var line = reader.readLine()
            while (line != null) {
                writer.write(line)
                line = reader.readLine()
            }
        } catch (e: Exception) {

        } finally {
            try {
                resourceReader.close()
            } catch (e: Exception) {

            }

        }

        jsonString = writer.toString()
    }

    /**
     * Build an object from the specified JSON resource using Gson.
     *
     * @param type The type of the object to build.
     *
     * @return An object of type T, with member fields populated using Gson.
     */
    fun <T> constructUsingGson(type: Class<T>): T {
        val gson = GsonBuilder().create()
        return gson.fromJson(jsonString, type)
    }

    companion object {
        private val LOGTAG = JSONResourceReader::class.java.simpleName
    }
}

