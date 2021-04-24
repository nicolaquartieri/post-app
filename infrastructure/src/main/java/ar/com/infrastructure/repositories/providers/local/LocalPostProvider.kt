package ar.com.infrastructure.repositories.providers.local

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.PostProvider

class LocalPostProvider: PostProvider {
    override suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        return emptyList()
    }
}