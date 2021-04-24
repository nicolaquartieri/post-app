package ar.com.infrastructure.repositories

import ar.com.infrastructure.repositories.providers.PostProvider

class PostRepository(private val remoteProvider: PostProvider) {
    suspend fun getPost(rowsOfPage: Int, page: Int) = remoteProvider.getAllPost(rowsOfPage, page)
}
