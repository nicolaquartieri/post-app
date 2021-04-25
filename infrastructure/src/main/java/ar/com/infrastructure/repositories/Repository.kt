package ar.com.infrastructure.repositories

import ar.com.domain.entities.Post

interface Repository {
    suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post>
}
