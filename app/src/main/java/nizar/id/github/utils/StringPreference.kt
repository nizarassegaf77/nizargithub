package nizar.id.github.utils

import android.R.id.edit
import android.content.SharedPreferences
import android.support.annotation.NonNull
import android.support.annotation.Nullable


public class StringPreference(val preferences: SharedPreferences,
                              val key: String,
                              var defaultValue: String) {

    @Nullable
    fun get(): String? {
        return preferences.getString(key, defaultValue)
    }

    fun isSet(): Boolean {
        return preferences.contains(key)
    }

    fun set(@Nullable value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun delete() {
        preferences.edit().remove(key).apply()
    }
}