package ar.com.infrastructure.repositories.providers.remote.impl

import ar.com.domain.entities.Post

interface PostProvider {
    fun getAllPost(rowsOfPage: Int, page: Int): List<Post>
}
