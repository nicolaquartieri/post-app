package ar.com.infrastructure.repositories.providers.configuration

interface ConfigurationProvider {
    fun getValue(key: String, default: String): String
    fun storeValue(key: String, value: String)
}