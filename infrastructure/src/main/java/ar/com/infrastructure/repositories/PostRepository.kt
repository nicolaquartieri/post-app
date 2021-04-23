package ar.com.infrastructure.repositories

import ar.com.infrastructure.repositories.providers.remote.impl.PostProvider

class PostRepository(private val remoteProvider: PostProvider) {
    fun getPost(rowsOfPage: Int, page: Int) = remoteProvider.getAllPost(rowsOfPage, page)
}
