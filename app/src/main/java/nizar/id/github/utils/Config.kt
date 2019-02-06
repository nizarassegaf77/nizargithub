package nizar.id.github.utils

import android.support.annotation.StringDef

/**
 *
 * Created by Nizar Assegaf on 3/2/2019.
 *
 */

@Retention(AnnotationRetention.SOURCE)
@StringDef(
        Config.BASE_URL
)
annotation class Configs

object Config {
    const val BASE_URL = "https://api.github.com/"
}