package ar.com.postapp.common

import ar.com.infrastructure.repositories.PostRepository
import ar.com.infrastructure.repositories.providers.memory.MemoryPostProvider
import ar.com.infrastructure.repositories.providers.remote.RemotePostProvider

object RepositoryProvider {
    fun inject() {
        postRepository
    }

    val postRepository by lazy { PostRepository(RemotePostProvider()) }
}