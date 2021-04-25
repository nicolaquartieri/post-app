package ar.com.postapp.common

import ar.com.infrastructure.repositories.PostRepository
import ar.com.infrastructure.repositories.providers.LocalPostProvider

object RepositoryProvider {
    fun inject() {
        repository
    }

    val repository by lazy { PostRepository(LocalPostProvider()) }

    /**
    val repository by lazy { CachePostRepository(LocalPostProvider(),
        RemotePostProvider(),
        LocalConfigurationProvider(PostApplication.INSTANCE.sharedPreferencesHelper)
    ) }
    */
}