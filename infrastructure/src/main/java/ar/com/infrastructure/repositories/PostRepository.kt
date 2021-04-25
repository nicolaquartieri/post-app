package ar.com.infrastructure.repositories

import ar.com.infrastructure.repositories.providers.PostProvider

open class PostRepository(val provider: PostProvider): Repository {
    override suspend fun getAllPost(rowsOfPage: Int, page: Int) = provider.getAllPost(rowsOfPage, page)
}
