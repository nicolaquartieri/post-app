package ar.com.infrastructure.repositories.providers

import ar.com.domain.entities.Post

interface PostProvider {
    suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post>
    suspend fun insertAllPost(posts: List<Post>): List<Long>
}
