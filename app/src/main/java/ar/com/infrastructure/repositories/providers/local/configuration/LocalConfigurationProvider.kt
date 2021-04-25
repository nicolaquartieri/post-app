package ar.com.infrastructure.repositories.providers.local.configuration

import android.content.SharedPreferences
import ar.com.infrastructure.repositories.providers.configuration.ConfigurationProvider

class LocalConfigurationProvider(private val sharedPreferences: SharedPreferences)
    : ConfigurationProvider {

    override fun getValue(key: String, default: String): String {
        var value: String? = default
        if (sharedPreferences.contains(key)) {
            value = sharedPreferences.getString(key, default)
        }
        return value ?: ""
    }

    override fun storeValue(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}