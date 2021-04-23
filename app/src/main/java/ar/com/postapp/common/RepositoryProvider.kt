package ar.com.postapp.common

import ar.com.infrastructure.repositories.PostRepository
import ar.com.infrastructure.repositories.providers.memory.MemoryPostProvider

object RepositoryProvider {
    fun inject() {
        postRepository
    }

    val postRepository by lazy { PostRepository(MemoryPostProvider()) }
}