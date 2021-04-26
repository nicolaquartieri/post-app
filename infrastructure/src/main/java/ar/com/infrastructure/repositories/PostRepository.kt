package ar.com.infrastructure.repositories

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.PostProvider
import ar.com.infrastructure.repositories.utils.Http.httpError

open class PostRepository(private val provider: PostProvider): Repository {
    override suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post> = httpError {
        provider.getAllPost(rowsOfPage, page)
    }
}
