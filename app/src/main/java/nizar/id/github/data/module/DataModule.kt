package nizar.id.github.data.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(var app: Application) {
    @Provides @Singleton
    protected fun  provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}