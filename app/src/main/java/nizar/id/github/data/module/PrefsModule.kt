package nizar.id.github.data.module

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import nizar.id.github.utils.StringPreference
import javax.inject.Singleton

@Module(includes = arrayOf(DataModule::class))
class PrefsModule {

    @Provides
    @Singleton
    fun provideValue(prefs: SharedPreferences):StringPreference {
        return  StringPreference(prefs, "myKey", "");
    }
}