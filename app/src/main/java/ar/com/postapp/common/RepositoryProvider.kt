package ar.com.postapp.common

import ar.com.PostApplication
import ar.com.infrastructure.repositories.CachePostRepository
import ar.com.infrastructure.repositories.providers.LocalPostProvider
import ar.com.infrastructure.repositories.providers.local.configuration.LocalConfigurationProvider
import ar.com.infrastructure.repositories.providers.remote.RemotePostProvider

object RepositoryProvider {
    fun inject() {
        repository
    }

    val repository by lazy { CachePostRepository(LocalPostProvider(),
        RemotePostProvider(),
        LocalConfigurationProvider(PostApplication.INSTANCE.sharedPreferencesHelper)
    ) }
}